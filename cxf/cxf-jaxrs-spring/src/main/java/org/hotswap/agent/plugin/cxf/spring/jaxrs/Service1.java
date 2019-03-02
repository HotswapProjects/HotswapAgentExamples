package org.hotswap.agent.plugin.cxf.spring.jaxrs;

import org.springframework.stereotype.Service;

@Service
public class Service1 {
     void doSomething() {
        System.out.println("Service1 print");
    }
}
