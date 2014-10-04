package org.hotswap.agent.example.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.hotswap.agent.example.model.TestEntity;

/**
 * Experiment with this service in debug mode to check Spring reloading.
 *
 * @author Vladimir Dvorak, Jiri Bubnik
 */
@Named
@SessionScoped
public class TestEntityService implements Serializable {

    private static final long serialVersionUID = 81172L;
    
    @Inject 
    private EntityManager entityManager;
    
    public void addTestEntity(TestEntity entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
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
