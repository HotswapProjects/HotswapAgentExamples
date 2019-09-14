package org.hotswap.agent.example.cxf.jaxrs;

import javax.ejb.Stateless;

@Stateless
public class StatelessBean {

    public String getHello() {
        return getHelloInt();
    }

    private String getHelloInt() {
        return "hello";
    }
}
