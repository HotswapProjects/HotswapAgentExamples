package org.hotswap.agent.example.deltaspike.beans;

import java.io.Serializable;

import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.apache.deltaspike.core.api.scope.ConversationGroup;
import org.apache.deltaspike.core.api.scope.GroupedConversationScoped;

@Named
@GroupedConversationScoped
@ConversationGroup(MyConversationGroup.class)
public class GroupedConvBean3 implements Serializable {

    private static final long serialVersionUID = -7175483534416650494L;

    private static int conversationCount = 0;

    private int conversationId;

    @Inject
    private HelloProducer1 helloProducer1;

    private int helloCount = 0;

    @Inject
    private HelloProducer2 helloProducer2;
//
//    @Inject
//    private HelloProducer1 helloProducer3;

    public GroupedConvBean3() {
        conversationId = conversationCount;
        conversationCount ++;
    }

    public String hello() {
        helloCount ++;
        return "GroupedConvBean3[conversationId=" + conversationId + ",helloCount=" + helloCount + "]:" + helloProducer2.hello();
    }

}
