package org.hotswap.agent.it.mvc;

import java.io.IOException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hotswap.agent.it.model.TestEntity;
import org.hotswap.agent.it.service.TestEntityService;

/**
 * Simple Spring MVC controller.
 *
 * Note, that Spring MVC is not fully supported for reload (e.g. endpoint mapping).
 */
@Named
@RequestScoped
public class IndexComponent {
    
    @Inject
    TestEntityService testEntityService;

    public String helloWorld() throws IOException {
        return testEntityService.helloWorld();
    }

    /**
     * Experiment with entities and Hibernate.
     */
    public List<TestEntity> getTestEntityList() {
        TestEntity a = new TestEntity(String.valueOf(System.currentTimeMillis()));
        a.setName("Tester ");
        testEntityService.addTestEntity(a);
        return testEntityService.loadTestEntities();
    }

}
