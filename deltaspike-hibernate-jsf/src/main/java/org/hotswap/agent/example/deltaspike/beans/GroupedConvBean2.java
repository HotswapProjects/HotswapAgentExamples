package org.hotswap.agent.example.deltaspike.beans;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.core.api.scope.ConversationGroup;
import org.apache.deltaspike.core.api.scope.GroupedConversationScoped;
import org.apache.deltaspike.core.spi.scope.conversation.GroupedConversationManager;

@Named
@GroupedConversationScoped
@ConversationGroup(MyConversationGroup.class)
public class GroupedConvBean2 implements Serializable {

    private static final long serialVersionUID = -3305275675889306638L;

    private static int conversationCount = 0;

    private int conversationId;

    @Inject
    private HelloProducer1 helloProducer1;

    @Inject
    private GroupedConversationManager conversationManager;

    private int helloCount = 0;

//    @Inject
//    private HelloProducer2 helloProducer2;
//
//    @Inject
//    private HelloProducer1 helloProducer3;

    public GroupedConvBean2() {
        conversationId = conversationCount;
        conversationCount ++;
    }

    public String hello() {
        helloCount ++;
        return "GroupedConvBean2[conversationId=" + conversationId + ",helloCount=" + helloCount + "]:" + helloProducer1.hello();
    }

    public void closeConversation() {
        conversationManager.closeConversationGroup(MyConversationGroup.class);
    }

}
