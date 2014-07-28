package org.hotswap.agent.example;

/**
 * Hello world class - this will be used to hotswap the package in extra.
 */
public class HelloWorldServletHotswap extends HelloWorldBaseServlet {
    @Override
    protected String getHello() {
        return "Hello World Hotswap";
    }
}
