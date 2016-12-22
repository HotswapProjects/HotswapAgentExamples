package org.hotswap.agent.example.jersey;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Jersey resource.
 */
@Path("/")
public class Jersey2Resource {

    @Inject
    TestService testService;

    @Inject
    TestService2 testService2;

    /**
     * Access this resource throw an HTTP request on /rest/jersey1/hello URI.
     *
     * @return 'Hello from Jersey1!' string
     */
    @GET
    @Path("/hello")
    public String hello() {
        return testService2.sayHello();
    }
}
