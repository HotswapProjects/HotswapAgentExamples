package org.hotswap.agent.example.plugin;

import org.hotswap.agent.example.service.HelloWorldService;

/**
 * This class should be used in the application classloader.
 * <p/>
 * Hotswap agent defines plugin classes in BOTH classloaders agent AND application. You need to know for which
 * classloader is each class targeted. In this example plugin, this is the only class targeted towards application
 * classloader.
 */
public class ReloadClassService {
    /**
     * Method invoked from ReloadClassCommand using reflection in application classloader.
     *
     * @param className        class name
     * @param helloWorldServiceObject object originally from the application, registered in the plugin. It is from
     *                         application classloader and hence cannot be typed in plugin classloader. Now
     *                         we are back in application classloader and can safely cast back to it's type.
     */
    public static void classReloaded(String className, Object helloWorldServiceObject) {

        // this is the main purpose of this class. Finally, we can use typed variables and
        // use normal code instead of reflection. Imagine more complex code to reload part of your
        // framework - it would be inconvenient to use reflection for each invocation.
        HelloWorldService helloWorldService = (HelloWorldService) helloWorldServiceObject;

        // call framework method directly
        helloWorldService.addReloadedClass(className);
    }
}
