package com.acme.dbo.txlog.message;

public class BoolMessage extends DecoratedMessage{

    private static final String MESSAGE_PREFIX = "primitive: ";

    public BoolMessage(boolean message) {
        super(MESSAGE_PREFIX, MessageType.BOOL);
        stringMessageContent = String.valueOf(message);
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