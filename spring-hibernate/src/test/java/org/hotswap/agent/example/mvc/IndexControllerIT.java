package org.hotswap.agent.example.mvc;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.hotswap.agent.example.service.TestRepository;
import org.hotswap.agent.util.test.WaitHelper;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.junit.Assert.assertEquals;

public class IndexControllerIT {
    String ENDPOINT = "http://localhost:8080/spring-hibernate/mvc/";

    @Test
    public void testUpAndRunning() throws Exception {
        HttpUriRequest request = new HttpGet(ENDPOINT + "hello");

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());

        assertEquals("Hello World", EntityUtils.toString(httpResponse.getEntity()));
    }

    @Test
    public void testHelloWord() throws IOException {
        final HttpUriRequest request = new HttpGet(ENDPOINT + "helloRepository");

        // replace the class file in extraClasspath (classloader looks into this directory)
        // autoHotswap should discover this change and reload the class
        String helloWorldFile = TestRepository.class.getName().replace(".", "/") + ".class";
        Path source = Paths.get("target/hotswap/" + helloWorldFile);
        Path target = Paths.get("target/classes/" + helloWorldFile);
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

        // wait for the hotswap
        boolean result = WaitHelper.waitForCommand(new WaitHelper.Command() {
            @Override
            public boolean result() throws Exception {
                HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
                return "Hello World Hotswap".equals(EntityUtils.toString(httpResponse.getEntity()));
            }
        });
    }
}