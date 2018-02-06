package org.hotswap.agent.example.deltaspike.beans;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ManagedBean implements Serializable {

    private static final long serialVersionUID = -3776778289861207553L;

    private static int idCount = 0;

    private int beanId;

    @Inject
    private HelloProducer1 helloProducer1;

//    @Inject
//    private HelloProducer2 helloProducer2;

    private int helloCount = 0;

    public ManagedBean() {
        beanId = idCount;
        idCount ++;
    }

    public String hello() {
        helloCount ++;
        return "ManagedBean[beanId=" + idCount + ",helloCount=" + helloCount + "]:" + helloProducer1.hello();
    }
}
