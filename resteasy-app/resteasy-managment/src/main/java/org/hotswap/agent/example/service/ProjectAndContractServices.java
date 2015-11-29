package org.hotswap.agent.example.service;

import java.util.Date;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.hotswap.agent.example.model.Person;

/**
 *
 * @author christoforosl
 */
@Path("/PersonManagement")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class ProjectAndContractServices {

    @GET
    @Path("Person")
    public Person getPerson() {

        final org.hotswap.agent.example.model.Person ret = new Person();
        ret.setBirthDate(new Date());
        ret.setFirstName("hotswap ZZZ");
        ret.setLastName("test");
        ret.setPersonId(1000L);

        return ret;
    }


}
