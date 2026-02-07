package org.hotswap.agent.example.deltaspike.beans;

import java.io.Serializable;

import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.apache.deltaspike.core.api.scope.WindowScoped;

@Named
@WindowScoped
public class WindowBean implements Serializable {

    private static final long serialVersionUID = 6608194853271664609L;

    private static int windowCount = 0;

    private int windowId;

    @Inject
    private HelloProducer1 helloProducer1;

    private int helloCount = 0;

//    @Inject
//    private HelloProducer2 helloProducer2;
//
//    @Inject
//    private HelloProducer1 helloProducer3;
//
    public WindowBean() {
        windowId = windowCount;
        windowCount ++;
    }

    public String hello() {
        helloCount ++;
        return "WindowBean[windowId=" + windowId + ",helloCount=" + helloCount + "]:" + helloProducer1.hello();
    }

}
