package org.hotswap.agent.example;

/**
 * Hello world class to be replaced by hotswap.
 */
public class HelloWorldServletHotswap extends HelloWorldBaseServlet {
    @Override
    protected String getHello() {
        return "Hello Worlda";
    }
}
