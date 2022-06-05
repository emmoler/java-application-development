package com.acme.dbo.txlog.message;

public class EmptyMessage extends DecoratedMessage {

    public EmptyMessage() {
        super(null, null);
    }

    @Override
    public boolean isAccumulatable(Message message) {
        return false;
    }

    @Override
    public Message accumulate(Message message) {
        throw new java.lang.UnsupportedOperationException("Not supported");
    }

    @Override
    public String getDecoratedContent() {
        return "";
    }
}
