package org.hotswap.agent.example.jersey;

import org.jvnet.hk2.annotations.Service;

@Service
public class TestService {
    public String sayHello() {
        return "hello";
    }
}
