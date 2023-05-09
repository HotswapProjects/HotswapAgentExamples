package org.hotswap.agent.example;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.hotswap.agent.util.test.WaitHelper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.junit.Assert.assertEquals;

/**
 * Test autoHotswap of classes after change
 */
public class HelloWorldServletHotswapIT {

    @Test
    public void extraClassPathHotswapTest() throws IOException, InterruptedException {
        final HttpUriRequest request = new HttpGet(HelloWorldServletIT.ENDPOINT + "helloHotswap");

        // replace the class file in extraClasspath (classloader looks into this directory)
        // autoHotswap should discover this change and reload the class
        String helloWorldFile = HelloWorldServletHotswap.class.getName().replace(".", "/") + ".class";
        Path source = Paths.get("target/hotswap/" + helloWorldFile);
        Path target = Paths.get("target/extra/" + helloWorldFile);
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

        // wait for the hotswap
        boolean result = WaitHelper.waitForCommand(new WaitHelper.Command() {
            @Override
            public boolean result() throws Exception {
                HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
                String result = EntityUtils.toString(httpResponse.getEntity());

                return "Hello World Hotswap".equals(result);
            }
        }, 2000);

        // check that agentexamples works
        Assert.assertTrue("Assert hotswapped class in extra is used.", result);
    }

}