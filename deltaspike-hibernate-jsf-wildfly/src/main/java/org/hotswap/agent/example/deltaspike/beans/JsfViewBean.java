package org.hotswap.agent.example.deltaspike.beans;

import java.io.Serializable;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class JsfViewBean implements Serializable {

    private static final long serialVersionUID = -8176648612947203701L;

    private static int idCount = 0;

    private int beanId;

    @Inject
    private HelloProducer1 helloProducer1;

    @Inject
    private HelloProducer2 helloProducer2;

    private int helloCount = 0;

    public JsfViewBean() {
        beanId = idCount;
        idCount ++;
    }

    public String hello() {
        helloCount ++;
        return "JsfViewBean[beanId=" + beanId + ",helloCount=" + helloCount + "]:" + helloProducer2.hello();
    }
}
