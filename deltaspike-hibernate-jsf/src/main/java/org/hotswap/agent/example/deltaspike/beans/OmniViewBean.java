package org.hotswap.agent.example.deltaspike.beans;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

@Named
@ViewScoped
public class OmniViewBean implements Serializable {

    private static final long serialVersionUID = -8176648612947203701L;

    private static int idCount = 0;

    private int beanId;

    @Inject
    private HelloProducer1 helloProducer1;

//    @Inject
//    private HelloProducer2 helloProducer2;

    private int helloCount = 0;

    public OmniViewBean() {
        beanId = idCount;
        idCount ++;
    }

    public String hello() {
        helloCount ++;
        return "OmniViewBean[beanId=" + beanId + ",helloCount=" + helloCount + "]:" + helloProducer1.hello();
    }
}
