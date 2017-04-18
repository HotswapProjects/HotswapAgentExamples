package org.hotswap.agent.example.jersey;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class Jersey2App {
    public static void main(String[] args) throws Exception {

        MyApplication config = new MyApplication();
        ServletHolder jerseyServlet
                        = new ServletHolder(new ServletContainer(config));

        Server server = new Server(8080);
        ServletContextHandler context
                = new ServletContextHandler(server, "/");
        context.addServlet(jerseyServlet, "/*");

        server.start();
        server.join();
    }
}
