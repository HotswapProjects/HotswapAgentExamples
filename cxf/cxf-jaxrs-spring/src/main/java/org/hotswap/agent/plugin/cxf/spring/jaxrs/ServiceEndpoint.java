package org.hotswap.agent.plugin.cxf.spring.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

@Path("/services")
public class ServiceEndpoint {

    @Autowired
    private Service1 service1;

    @Autowired
    private Service2 service2;

    @Path("/service1")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response service1(@QueryParam("echo") final String payload) {
        service2.doSomething();
        return Response.ok(payload).build();
    }

//    @Path("/service2")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response service2() {
//        service1.doSomething();
//        return Response.ok().build();
//    }
}
