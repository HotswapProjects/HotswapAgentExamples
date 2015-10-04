package org.hotswap.agent.example.ejb;

import java.util.List;

import org.hotswap.agent.example.model.TestEntity;

public interface TestEntityService {

	public void addTestEntity(TestEntity entity);

    public List<TestEntity> loadTestEntities();

    public String helloWorld();

    public void addReloadedClass();
}
