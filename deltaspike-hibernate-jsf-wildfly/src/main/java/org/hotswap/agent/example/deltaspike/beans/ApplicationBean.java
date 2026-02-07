package org.hotswap.agent.example.deltaspike.beans;

import java.io.Serializable;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.spi.AnnotatedType;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.apache.deltaspike.core.util.Annotateds;
import org.hotswap.agent.example.deltaspike.jpa.UserRepository;
import org.omnifaces.cdi.Startup;

@Named
@ApplicationScoped
public class ApplicationBean implements Serializable {

    private static final long serialVersionUID = -8572299300825949098L;

    @Inject
    private HelloProducer1 helloProducer1;

    @PostConstruct
    public void onStart()
    {
        AnnotatedType<UserRepository> createAnnotatedType = CDI.current().getBeanManager().createAnnotatedType(UserRepository.class);
        String createTypeId = Annotateds.createTypeId(createAnnotatedType);
        System.out.println(createTypeId);
    }

//    @Inject
//    private HelloProducer2 helloProducer2;

    private int helloCount = 0;

    public String hello() {
        helloCount ++;
        return "ApplicationBean[helloCount=" + helloCount + "]:" + helloProducer1.hello();
    }

}
