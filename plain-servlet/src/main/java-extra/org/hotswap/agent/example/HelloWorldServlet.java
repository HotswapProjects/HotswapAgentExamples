package org.hotswap.agent.example;

/**
 * Simple Hello World - this should override the default.
 */
public class HelloWorldServlet extends HelloWorldBaseServlet {
    @Override
    protected String getHello() {
        return "Hello World Extra";
    }
}
