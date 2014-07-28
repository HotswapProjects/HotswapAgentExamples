package org.hotswap.agent.example;

import org.hotswap.agent.util.test.WaitHelper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Test autoHotswap of classes after change
 */
public class HelloWorldHotswapTest {
    @Test
    public void extraClassPathHotswapTest() throws IOException, InterruptedException {

        // TODO rerun of the test will fail, because hotswapped class is already in place. "mvn clean install" is currently needed to run the test correctly
        // Assert.assertEquals("Assert class from target/extra is used.", "Hello World Extra", HelloWorldHotswap.hello());

        // replace the class file in extraClasspath (classloader looks into this directory)
        // autoHotswap should discover this change and reload the class
        String helloWorldFile = HelloWorldHotswap.class.getName().replace(".", "/") + ".class";
        Path source = Paths.get("target/hotswap/" + helloWorldFile);
        Path target = Paths.get("target/extra/" + helloWorldFile);
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

        // wait for the hotswap
        boolean result = WaitHelper.waitForCommand(new WaitHelper.Command() {
            @Override
            public boolean result() throws Exception {
                return "Hello World Hotswap".equals(HelloWorldHotswap.hello());
            }
        });

        // check that it works
        Assert.assertTrue("Assert hotswapped class in extra is used.", result);
    }
}