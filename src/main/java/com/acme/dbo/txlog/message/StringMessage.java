package com.acme.dbo.txlog.message;

public class StringMessage extends DecoratedMessage {

    private static final String MESSAGE_PREFIX = "string: ";
    private int accumulatorCount = 1;

    public StringMessage(String message) {
        super(MESSAGE_PREFIX, MessageType.STRING);
        stringMessageContent = message;
    }

    public StringMessage(String message, int accumulator) {
        super(MESSAGE_PREFIX, MessageType.STRING);
        stringMessageContent = message;
        this.accumulatorCount = accumulator;
    }

    @Override
    public String getDecoratedContent() {
        String retVal;
        if (accumulatorCount == 1) retVal = stringMessageContent;
        else { retVal =  String.format("%s (x%s)", stringMessageContent, accumulatorCount); }
        return decorate(retVal);
    }

    @Override
    public boolean isAccumulatable(Message message) {
        return getContent().equals(message.getContent());
    }

    @Override
    public Message accumulate(Message message) {
        accumulatorCount++;
        return new StringMessage(stringMessageContent, accumulatorCount);
    }
}
