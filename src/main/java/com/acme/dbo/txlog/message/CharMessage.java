package com.acme.dbo.txlog.message;

public class CharMessage extends DecoratedMessage {

    private static final String MESSAGE_PREFIX = "char: ";

    public CharMessage(char message) {
        super(MESSAGE_PREFIX, MessageType.CHAR);
        super.stringMessageContent = String.valueOf(message);
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
