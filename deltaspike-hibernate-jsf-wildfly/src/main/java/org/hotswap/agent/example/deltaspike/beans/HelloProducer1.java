package org.hotswap.agent.example.deltaspike.beans;

import java.io.Serializable;

import jakarta.enterprise.context.Dependent;

@Dependent
public class HelloProducer1 implements Serializable {

    private static final long serialVersionUID = 3869560189111153965L;

    public String hello() {
        return "HelloProducer1:hello";
    }
}
