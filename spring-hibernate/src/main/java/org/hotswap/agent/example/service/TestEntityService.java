package org.hotswap.agent.example.service;

import org.hotswap.agent.example.model.TestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Experiment with this service in debug mode to check Spring reloading.
 *
 * @author Jiri Bubnik
 */
@Service
@Transactional
public class TestEntityService {

    @Autowired
    TestRepository testRepository;

    @PersistenceContext
    EntityManager entityManager;


    public void addTestEntity(TestEntity entity) {
        entityManager.persist(entity);
    }

    @SuppressWarnings("unchecked")
    public List<TestEntity> loadTestEntities() {
        return entityManager.createQuery("select e from TestEntity e").getResultList();
    }

    public String helloWorld() {
        return "Hello World";
    }

    // return text from repository and ExamplePlugin values.
    public String helloWorldRepository() {
        return testRepository.helloWorld();
    }

    /**
     * See ExamplePlugin
     */
    String examplePluginResourceText = "";
    int loadedClasses = 0;
    int reloadedClasses = 0;

    public void setExamplePluginResourceText(String examplePluginResourceText) {
        this.examplePluginResourceText = examplePluginResourceText;
    }

    public void setLoadedClasses(Integer loadedClasses) {
        this.loadedClasses = loadedClasses;
    }

    public void addReloadedClass() {
        reloadedClasses++;
    }
}
