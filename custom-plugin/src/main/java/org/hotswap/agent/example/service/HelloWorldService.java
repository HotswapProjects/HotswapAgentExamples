package org.hotswap.agent.example.service;

import java.util.HashSet;
import java.util.Set;

/**
 * Example service which is watched for changes and invoked from the plugin.
 */
public class HelloWorldService {
    // Add each reloaded class (by hotswap or watcher)
    Set<String> reloadedClasses = new HashSet<String>();

    // count of loaded classes
    int loadedClasses;

    // text from examplePlugin.resource, it should be automatically updated after the resource is modified
    String examplePluginResourceText;

    public String getExamplePluginResourceText() {
        return examplePluginResourceText;
    }

    public void setExamplePluginResourceText(String examplePluginResourceText) {
        this.examplePluginResourceText = examplePluginResourceText;
    }

    public int getLoadedClasses() {
        return loadedClasses;
    }

    public void setLoadedClasses(int loadedClasses) {
        this.loadedClasses = loadedClasses;
    }

    public void addReloadedClass(String className) {
        reloadedClasses.add(className);
    }

    public Set<String> getReloadedClasses() {
        return reloadedClasses;
    }
}
