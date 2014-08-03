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
import java.util.ResourceBundle;

/**
 * Test loading classes and resources according to extraClasspath property.
 */
public class HelloWorldTest {
    @Test
    public void extraClassPathClassStaticTest() {
        Assert.assertEquals("Assert class from target/extra is used.", "Hello World Extra", HelloWorld.hello());
    }

    @Test
    public void extraClassPathClassTest() {
        Assert.assertEquals("Assert class from target/extra is used.", "Hello Extra par", new HelloWorld().hello("par"));
    }

    @Test
    public void extraClassPathAnnotationTest() throws Exception {
        Class clazz = HelloWorld.class;

        HelloAnnotation clazzAnnotation = (HelloAnnotation) clazz.getAnnotation(HelloAnnotation.class);
        Assert.assertEquals("hello extra", clazzAnnotation.value());

        Method helloMethod = clazz.getDeclaredMethod("hello", String.class);
        HelloAnnotation methodAnnotation = (HelloAnnotation) helloMethod.getAnnotation(HelloAnnotation.class);
        Assert.assertEquals("hello extra", methodAnnotation.value());
        HelloAnnotation paramAnnotation = (HelloAnnotation) helloMethod.getParameterAnnotations()[0][0];
        Assert.assertEquals("par extra", paramAnnotation.value());
    }

    @Test
    public void extraClassPathResourceTest() {
        String hello = ResourceBundle.getBundle("test").getString("hello");
        Assert.assertEquals("Assert resource from target/extra is used.", "Hello World Extra", hello);
    }

    @Test
    public void extraClassPathWatchResourceTest() throws IOException, InterruptedException {
        // check before change
        String hello = ResourceBundle.getBundle("testWatch").getString("hello");
        Assert.assertEquals("Assert original resource from target/classes is used.", "Hello World", hello);

        // replace the file in watched dir
        Path source = Paths.get("target/watch/testWatchReplace.properties");
        Path target = Paths.get("target/watch/testWatch.properties");
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

        // wait for the hotswap
        boolean result = WaitHelper.waitForCommand(new WaitHelper.Command() {
            @Override
            public boolean result() throws Exception {
                ResourceBundle.clearCache();
                String hello = ResourceBundle.getBundle("testWatch").getString("hello");
                return "Hello World Watch".equals(hello);
            }
        });

        // check that agentexamples works
        Assert.assertTrue("Assert modified target/watch/testWatch.properties is used.", result);
    }

}
