package org.hotswap.agent.example;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Test webapp property.
 */
public class HelloWorldServletJspIT {

    @Test
    public void extraWebappDirHotswapTest() throws IOException, InterruptedException {
        final HttpUriRequest request = new HttpGet(HelloWorldServletIT.ENDPOINT + "hello.jsp");
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());

        assertEquals("Hello World Extra", EntityUtils.toString(httpResponse.getEntity()));
    }

}