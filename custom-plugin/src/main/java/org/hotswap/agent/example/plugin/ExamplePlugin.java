package org.hotswap.agent.example.plugin;

import org.hotswap.agent.annotation.*;
import org.hotswap.agent.command.ReflectionCommand;
import org.hotswap.agent.command.Scheduler;
import org.hotswap.agent.javassist.CannotCompileException;
import org.hotswap.agent.javassist.CtClass;
import org.hotswap.agent.javassist.NotFoundException;
import org.hotswap.agent.logging.AgentLogger;
import org.hotswap.agent.util.IOUtils;
import org.hotswap.agent.util.PluginManagerInvoker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;

import static org.hotswap.agent.annotation.FileEvent.CREATE;
import static org.hotswap.agent.annotation.LoadEvent.DEFINE;
import static org.hotswap.agent.annotation.LoadEvent.REDEFINE;

/**
 * The plugin system annotation is similar to Spring MVC way - use method annotation with variable
 * method attributes types. See each annotation javadoc for available attribute types and usage.
 * <p/>
 * Always be aware of which classloader your code use (Application or agent classloader?) More on
 * classloader issues in
 * <a href="https://github.com/HotswapProjects/HotswapAgent/blob/master/HotswapAgent/README.md">Agent documentation</a>
 */
@Plugin(name = "ExamplePlugin", description = "Hotswap agent plugin as part of normal application.",
        testedVersions = "Describe dependent framework version you have tested the plugin with.",
        expectedVersions = "Describe dependent framework version you expect to work the plugin with.")
public class ExamplePlugin {
    public static final String PLUGIN_PACKAGE = "org.hotswap.agent.example";

    // as an example, we will enhance this service to return content of examplePlugin.resource
    // and class load/reload counts in agentexamples's helloWorld service method
    public static final String HELLO_WORLD_SERVICE = "org.hotswap.agent.example.service.HelloWorldService";

    // Agent logger is a very simple custom logging mechanism. Do not use any common logging framework
    // to avoid compatibility and classloading issues.
    private static AgentLogger LOGGER = AgentLogger.getLogger(ExamplePlugin.class);

    /**
     * Any plugin has to have at least one static @Transform method to hook initialization code. It is usually
     * some key framework method. Call PluginManager.initializePlugin() to create new plugin instance and
     * initialize agentexamples with the application classloader. Than call one or more methods on the plugin
     * to pass reference to framework/application objects.
     *
     * @param ctClass see @Transform javadoc for available parameter types. CtClass is convenient way
     *                to enhance method bytecode using javaasist
     */
    @OnClassLoadEvent(classNameRegexp = HELLO_WORLD_SERVICE)
    public static void transformTestEntityService(CtClass ctClass) throws NotFoundException, CannotCompileException {

        // You need always find a place from which to initialize the plugin.
        // Initialization will create new plugin instance (notice that transformTestEntityService is
        // a static method), inject agent services (@Inject) and register event listeners (@Transform and @Watch).
        String src = PluginManagerInvoker.buildInitializePlugin(ExamplePlugin.class);

        // If you need to call a plugin method from application context, there are some issues
        // Always think about two different classloaders - application and agent/plugin. The parameter
        // here cannot be of type TestEntityService because the plugin does not know this type at runtime
        // (although agentexamples will compile here!). If you call plugin method, usually only basic java types (java.lang.*)
        // are safe.
        src += PluginManagerInvoker.buildCallPluginMethod(ExamplePlugin.class, "registerService", "this", "java.lang.Object");

        // do enhance default constructor using javaasist. Plugin manager (TransformHandler) will use enhanced class
        // to replace actual bytecode.
        ctClass.getDeclaredConstructor(new CtClass[0]).insertAfter(src);

        LOGGER.debug(HELLO_WORLD_SERVICE + " has been enhanced.");
    }

    /**
     * All compiled code in ExamplePlugin is executed in agent classloader and cannot access
     * framework/application classes. If you need to call a method on framework class, use application
     * classloader. It is injected on plugin initialization.
     */
    @Init
    ClassLoader appClassLoader;

    /**
     * Called from HelloWorldService enhanced constructor. Note that the service cannot be typed to
     * HelloWorldService class - the class is not known to agent classloader (agentexamples lives only in the
     * application classloader).
     */
    public void registerService(Object helloWorldService) {
        this.helloWorldService = helloWorldService;
        LOGGER.info("Plugin {} initialized on service {}", getClass(), this.helloWorldService);
    }

    // the service, please note that agentexamples cannot be typed here
    Object helloWorldService;


    // Scheduler service - use to run a command asynchronously and merge multiple similar commands to one execution
    // static  - Scheduler and other agent services are available even in static context (before the plugin is initialized)
    @Init
    Scheduler scheduler;


    /**
     * Use @Watch annotation to register resource event listener (using file NIO events). See @Watch javadoc
     * for available method parameter types.
     */
    @OnResourceFileEvent(path = "examplePlugin.resource")
    public void watchForResourceChange(URI uri) throws ClassNotFoundException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NotFoundException, CannotCompileException {
        // simple example - read new value and set agentexamples via reflection
        String examplePluginResourceText = new String(IOUtils.toByteArray(uri));

        Method setExamplePluginResourceText = appClassLoader.loadClass(HELLO_WORLD_SERVICE)
                .getDeclaredMethod("setExamplePluginResourceText", String.class);
        setExamplePluginResourceText.invoke(helloWorldService, examplePluginResourceText);

        LOGGER.info("Service examplePluginResourceText value changed to {}", examplePluginResourceText);
    }
    int reloadedClasses = 0;

    /**
     * Count how many classes were loaded after the plugin is initialized (after HELLO_WORLD_SERVICE constructor).
     */
    @OnClassLoadEvent(classNameRegexp = PLUGIN_PACKAGE +  ".*", events = DEFINE)
    public void loadClass() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // you can use scheduler to run framework method asynchronously
        // there is convenient ReflectionCommand, which runs method in the target classloader, the reflection
        // logic is similar to code in watchForResourceChange (see ReflectionCommand source).
        scheduler.scheduleCommand(
                new ReflectionCommand(helloWorldService, "setLoadedClasses", ++reloadedClasses)
        );
    }

    /**
     * Count how many classes were reloaded via hotswap after the plugin is initialized.
     *
     * (Note - if you test the behaviour and reload TestEntityService or TestRepository - the spring bean
     *  gets reloaded and new instance is created, try the behaviour).
     */
    @OnClassLoadEvent(classNameRegexp = ".*", events = REDEFINE)
    public void reloadClass(String className) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // run the logic in a command. Multiple reload commands may be merged into one execution (see the command class)
        scheduler.scheduleCommand(new ReloadClassCommand(appClassLoader, className, helloWorldService));
    }

    /**
     * Watch for .class creation. This is quite common if you use classpath scanning frameworks (Spring, Hibernate, ..)
     * New class file is not known to the framework classloading process and hence not processed until instructed
     * explicitly by the plugin.
     * One caveat - although only CREATE type is watched for, compile process will generate DELETE/CREATE sequence
     * for modification.
     */
    @OnClassFileEvent(classNameRegexp = PLUGIN_PACKAGE + ".*", events = CREATE)
    public void changeClassFile(String className) {
        // Schedule command with longer timeout to wait for other similar commands and merge them into single event
        scheduler.scheduleCommand(new ReloadClassCommand(appClassLoader, className, helloWorldService), 500);
    }
}
