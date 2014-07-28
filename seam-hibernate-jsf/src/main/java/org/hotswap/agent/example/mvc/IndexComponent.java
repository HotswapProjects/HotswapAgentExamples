package org.hotswap.agent.example.mvc;

import java.io.IOException;
import java.util.List;

import org.hotswap.agent.example.model.TestEntity;
import org.hotswap.agent.example.service.TestEntityService;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

/**
 * Simple Spring MVC controller.
 *
 * Note, that Spring MVC is not fully supported for reload (e.g. endpoint mapping).
 */
@Name("indexComponent")
public class IndexComponent {
    
    @In(create=true)
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