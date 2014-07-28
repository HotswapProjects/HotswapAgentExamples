package org.hotswap.agent.example.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.hotswap.agent.example.model.TestEntity;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

/**
 * Experiment with this service in debug mode to check Spring reloading.
 *
 * @author Vladimir Dvorak, Jiri Bubnik
 */
@Name("testEntityService")
@Scope(ScopeType.SESSION)
public class TestEntityService {

    @In
    EntityManager entityManager;


    public void addTestEntity(TestEntity entity) {
        entityManager.persist(entity);
        entityManager.flush();
    }

    @SuppressWarnings("unchecked")
    public List<TestEntity> loadTestEntities() {
        List<TestEntity> result = entityManager.createQuery("select e from TestEntity e").getResultList();
        return result;
    }

    public String helloWorld() {
    // return text from repository and ExamplePlugin values.
        String hello = "Hello world";

        if (examplePluginResourceText != null)
            hello += "\n" + examplePluginResourceText;

        if (loadedClasses > 0)
            hello += "\nLoaded classes since plugin initialization: " + loadedClasses;

        if (reloadedClasses > 0)
            hello += "\nReloaded classes since plugin initialization: " + reloadedClasses;

        return hello;
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
