package com.acme.dbo.txlog.message;

public class CharMessage extends DecoratedMessage {

    private static final String MESSAGE_PREFIX = "char: ";
    private String ACCUMULATOR = "";

    public CharMessage(char message) {
        super(MESSAGE_PREFIX);
        ACCUMULATOR = String.valueOf(message);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.CHAR;
    }

    @Override
    public String getContent() {
        return String.valueOf(ACCUMULATOR);
    }

    @Override
    public boolean canAccumulate(Message message) {
        return false;
    }
}
