package org.hotswap.agent.example;

import org.hotswap.agent.util.test.WaitHelper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Test autoHotswap of classes after change
 */
public class HelloWorldHotswapTest {
    @Test
    public void extraClassPathHotswapTest() throws Exception {
        copyClassFile("org.hotswap.agent.example.HelloWorldHotswap", true);
        copyClassFile("org.hotswap.agent.example.HelloWorldHotswap$1", true); // anonymous Cloneable class in dummy() method
        copyClassFile("org.hotswap.agent.example.HelloWorldHotswap$2", true); // anonymous HelloInterface class in hello(String) method


        Assert.assertEquals("Assert class from target/extra is used.", "Hello World Extra", HelloWorldHotswap.hello());

        // force load anonymous inner classes
        new HelloWorldHotswap().dummy();
        Assert.assertEquals("Hello Extra par", new HelloWorldHotswap().hello("par"));

        // replace the class file in extraClasspath (classloader looks into this directory)
        // autoHotswap should discover this change and reload the class
        copyClassFile(HelloWorldHotswap.class.getName(), false);
        copyClassFile(HelloWorldHotswap.class.getName() + "$1", false); // anonymous Cloneable class in dummy() method
        copyClassFile(HelloWorldHotswap.class.getName() + "$2", false); // anonymous HelloInterface class in hello(String) method
        Thread.sleep(2000);

        // wait for the hotswap
        WaitHelper.waitForCommand(new WaitHelper.Command() {
            @Override
            public boolean result() throws Exception {
                return "Hello World Hotswap".equals(HelloWorldHotswap.hello());
            }
        });

        // check that it works
        Assert.assertEquals("Assert hotswapped class in extra is used.", "Hello World Hotswap", HelloWorldHotswap.hello());

        Assert.assertEquals("Hello Hotswap par", new HelloWorldHotswap().hello("par"));

        checkAnnotations(HelloWorldHotswap.class);
        // java-hotswap version of anonymous class should have the annotations as well
        checkAnnotations(getClass().getClassLoader().loadClass(HelloWorldHotswap.class.getName() + "$2"));
    }

    private void copyClassFile(String name, boolean fromBackup) throws IOException {
        String fileName = name.replace(".", "/") + ".class";
        Path source = Paths.get((fromBackup ? "target/extraBackup/" : "target/hotswap/") + fileName);
        Path target = Paths.get("target/extra/" + fileName);
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
    }

    private void checkAnnotations(Class clazz) throws Exception {
        HelloAnnotation clazzAnnotation = (HelloAnnotation) clazz.getAnnotation(HelloAnnotation.class);
        if (clazzAnnotation != null) // clazzAnnotation is not available for anonymous inner class
            Assert.assertEquals("hello hotswap", clazzAnnotation.value());

        Method helloMethod = clazz.getDeclaredMethod("hello", String.class);
        HelloAnnotation methodAnnotation = (HelloAnnotation) helloMethod.getAnnotation(HelloAnnotation.class);
        Assert.assertEquals("hello hotswap", methodAnnotation.value());
        HelloAnnotation paramAnnotation = (HelloAnnotation) helloMethod.getParameterAnnotations()[0][0];
        Assert.assertEquals("par hotswap", paramAnnotation.value());
    }

}