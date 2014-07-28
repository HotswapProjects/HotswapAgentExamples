package org.hotswap.agent.example;

import javax.servlet.*;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Base servlet with template method and several resources.
 */
public abstract class HelloWorldBaseServlet implements Servlet {

    protected HelloWorldBaseServlet() {
    }

    /**
     * Template method to return Hello  World text modifications.
     */
    protected abstract String getHello();

    /**
     * Return basic resource, it should be handled from target/extra directory.
     */
    protected String getHelloResource() {
        ResourceBundle.clearCache();
        return ResourceBundle.getBundle("test").getString("hello");
    }

    /**
     * Return content of testWatch.properties. It should be resolved to target/classes until
     * resources-watch/testWatch.properties is modified, then it should return the new value.
     */
    protected String getHelloResourceWatch() {
        ResourceBundle.clearCache();
        return ResourceBundle.getBundle("testWatch").getString("hello");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        String param = req.getParameter("method");
        if ("helloResource".equals(param)) {
            res.getWriter().write(getHelloResource());
        } else if ("helloResourceWatch".equals(param)) {
            res.getWriter().write(getHelloResourceWatch());
        } else {
            // default hello world
            res.getWriter().write(getHello());
        }
    }


    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
