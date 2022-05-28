package com.acme.dbo.txlog.message;

public class BoolMessage extends DecoratedMessage{

    private static final String MESSAGE_PREFIX = "primitive: ";
    private boolean CONTENT;

    public BoolMessage(boolean message) {
        super(MESSAGE_PREFIX);
        CONTENT = message;
    }

    @Override
    public String getContent() {
        return String.valueOf(CONTENT);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.BOOL;
    }
}