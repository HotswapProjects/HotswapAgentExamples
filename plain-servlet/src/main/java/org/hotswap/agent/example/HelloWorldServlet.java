package org.hotswap.agent.example;

/**
 * Simple Hello World to be replaced by extra
 */
public class HelloWorldServlet extends HelloWorldBaseServlet {

    @Override
    protected String getHello() {
        return "Hello World";
    }

}
