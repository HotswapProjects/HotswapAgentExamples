package org.hotswap.agent.example.deltaspike.beans;

import java.io.Serializable;

import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.apache.deltaspike.core.api.scope.GroupedConversationScoped;
import org.apache.deltaspike.core.spi.scope.conversation.GroupedConversationManager;

@Named
@GroupedConversationScoped
public class GroupedConvBean1 implements Serializable {

    private static final long serialVersionUID = 3051650311158372971L;

    private static int conversationCount = 0;

    private int conversationId;

    @Inject
    private HelloProducer1 helloProducer1;

    @Inject
    private HelloProducer2 helloProducer2;

    @Inject
    private GroupedConversationManager conversationManager;

    private int helloCount = 0;

    public GroupedConvBean1() {
        conversationId = conversationCount;
        conversationCount ++;
    }

    public String hello() {
        helloCount ++;
        return "GroupedConvBean1[conversationId=" + conversationId + ",helloCount=" + helloCount + "]:" + helloProducer2.hello();
    }

    public void closeConversation() {
        conversationManager.closeConversationGroup(getClass());
    }

    public void closeAllWindowConversations() {
        conversationManager.closeConversations();
    }

}
