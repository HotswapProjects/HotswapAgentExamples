package org.hotswap.agent.example.deltaspike.appl;

import java.io.Serializable;

import jakarta.enterprise.context.Conversation;
import jakarta.inject.Inject;

import org.primefaces.event.SelectEvent;

public abstract class MasterDetailControllerBase<T> implements Serializable {

    private static final long serialVersionUID = 5824242841963664386L;

    protected static final int LIST_LEVEL = 1;

    protected static final int DETAIL_LEVEL = 2;

    private int detailLevel = LIST_LEVEL;

    private T detail;

    @Inject
    private Conversation conversation;

    public void clearDetail() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    public void selectNewDetail() {
        selectDetail(null);
    }

    public void selectDetail(T detail) {
        this.detail = detail;

        if (!conversation.isTransient()) {
            conversation.end();
        }
        conversation.begin();

        onDetailSelected(detail);
    }

    @SuppressWarnings("unchecked")
    public void onSelectDetailEvent(SelectEvent event) {
        selectDetail((T) event.getObject());
        detailLevel = DETAIL_LEVEL;
    }

    public T getSelection() {
        return detail;
    }

    public void setSelection(T selection) {
        // Nothing to do
    }

    abstract protected void onDetailSelected(T detail);

    public T getDetail() {
        return detail;
    }

    protected void setDetail(T detail) {
        this.detail = detail;
    }

    public int getDetailLevel() {
        return detailLevel;
    }

    public void setDetailLevel(int detailLevel) {
        this.detailLevel = detailLevel;
    }

}
