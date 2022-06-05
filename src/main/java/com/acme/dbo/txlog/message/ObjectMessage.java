package com.acme.dbo.txlog.message;

public class ObjectMessage extends DecoratedMessage {

    private static final String MESSAGE_PREFIX = "reference: ";

    public ObjectMessage(Object message) {
        super(MESSAGE_PREFIX, String.valueOf(message));
    }

    @Override
    public boolean isAccumulatable(Message message) {
        return false;
    }

    @Override
    public Message accumulate(Message message) {
        throw new java.lang.UnsupportedOperationException("Not supported");
    }
}
