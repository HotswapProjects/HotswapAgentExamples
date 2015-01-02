package org.hotswap.agent.exemple.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Jersey resource.
 */
@Path("/jersey1")
public class Jersey1Resource {

    /**
     * Access this resource throw an HTTP request on /rest/jersey1/hello URI.
     *
     * @return 'Hello from Jersey1!' string
     */
    @GET
    @Path("/hello")
    public String hello() {
        return "Hello from Jersey 1!";
    }
}
