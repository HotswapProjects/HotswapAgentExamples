package org.hotswap.agent.example.deltaspike.em;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class EntityManagerFactoryProducer {
    @Produces
    @ApplicationScoped
    public EntityManagerFactory create() {
        return Persistence.createEntityManagerFactory("testPU");
    }

    public void destroy(@Disposes EntityManagerFactory factory) {
        factory.close();
    }
}
