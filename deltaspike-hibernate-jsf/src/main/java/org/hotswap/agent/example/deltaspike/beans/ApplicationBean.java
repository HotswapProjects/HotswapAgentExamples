package org.hotswap.agent.example.deltaspike.beans;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ApplicationBean implements Serializable {

    private static final long serialVersionUID = -8572299300825949098L;

    @Inject
    private HelloProducer1 helloProducer1;

//    @Inject
//    private HelloProducer2 helloProducer2;

    private int helloCount = 0;

    public String hello() {
        helloCount ++;
        return "WindowBean[helloCount=" + helloCount + "]:" + helloProducer1.hello();
    }

}
