package org.hotswap.agent.example.jersey;

import org.glassfish.jersey.server.ResourceConfig;

public class MyApplication extends ResourceConfig {
    public MyApplication() {
        register(new MyApplicationBinder());
        packages(true, "org.hotswap.agent.example.jersey");
    }}
