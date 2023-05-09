package org.hotswap.agent.example;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
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
import java.util.ResourceBundle;

import static org.junit.Assert.assertEquals;

public class HelloWorldServletIT {
    public static final String ENDPOINT = "http://localhost:8080/plain-servlet/";

    @Test
    public void testUpAndRunning() throws Exception {
        HttpUriRequest request = new HttpGet(ENDPOINT + "hello");
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
    }

    @Test
    public void testHello() throws Exception {
        HttpUriRequest request = new HttpGet(ENDPOINT + "hello");
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        assertEquals("Hello World Extra", EntityUtils.toString(httpResponse.getEntity()));
    }

    @Test
    public void testHelloResourceExtraPath() throws Exception {
        HttpUriRequest request = new HttpGet(ENDPOINT + "hello?method=helloResource");
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        assertEquals("Hello World Extra", EntityUtils.toString(httpResponse.getEntity()));
    }

    @Test
    public void extraClassPathWatchResourceTest() throws IOException, InterruptedException {
        final HttpUriRequest request = new HttpGet(ENDPOINT + "hello?method=helloResourceWatch");

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
                HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

                String hello = EntityUtils.toString(httpResponse.getEntity());
                System.err.println(hello);
                return "Hello World Watch".equals(hello);
            }
        }, 4000);

        // check that agentexamples works
        Assert.assertTrue("Assert modified target/watch/testWatch.properties is used.", result);
    }
}