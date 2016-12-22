package org.hotswap.agent.example.jersey;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

@Service
public class TestService2 {

//    @Inject
//    TestService3 service3;

    public String sayHello() {
        return "hello2";
//        return service3.sayHello();
    }
}
