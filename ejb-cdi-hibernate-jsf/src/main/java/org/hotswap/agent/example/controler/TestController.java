package org.hotswap.agent.example.controler;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.hotswap.agent.example.ejb.TestEntityService;
import org.hotswap.agent.example.model.TestEntity;

public class TestController {
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
