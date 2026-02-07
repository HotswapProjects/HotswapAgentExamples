package org.hotswap.agent.example.deltaspike.beans;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;

@SessionScoped
public class HelloProducer2 implements Serializable {

    private static final long serialVersionUID = 1426463110054473736L;

    public String hello() {
        return "HelloProducer2:hello";
    }
}
