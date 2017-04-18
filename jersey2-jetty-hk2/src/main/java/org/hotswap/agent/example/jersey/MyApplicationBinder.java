package org.hotswap.agent.example.jersey;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class MyApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(TestService.class).to(TestService.class);
        bind(TestService2.class).to(TestService2.class);
        bind(TestService3.class).to(TestService3.class);
    }
}