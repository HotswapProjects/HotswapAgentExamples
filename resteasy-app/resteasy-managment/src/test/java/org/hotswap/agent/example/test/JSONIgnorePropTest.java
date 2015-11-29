package org.hotswap.agent.example.test;

import java.util.Date;

import org.codehaus.jackson.map.ObjectMapper;
import org.hotswap.agent.example.model.Person;
import org.junit.Test;

/**
 *
 * @author christoforosl
 */
public class JSONIgnorePropTest {

    @Test
    public void ignoredProperties() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        Person e = new Person();
        e.setBirthDate(new Date());

        String s = mapper.writeValueAsString(e);

    }
}
