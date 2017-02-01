package org.hotswap.agent.example.deltaspike;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

//    @Inject
//    private EntityManagerFactory emf;

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // Do nothing
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        // initialize entity manager factory on application statup
//        emf.createEntityManager();
    }

}
