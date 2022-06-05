package com.acme.dbo.txlog.message;

public class StringMessage extends DecoratedMessage {

    private static final String MESSAGE_PREFIX = "string: ";
    private final int accumulatorCount;

    public StringMessage(String message) {
        super(MESSAGE_PREFIX, message);
        accumulatorCount = 1;
    }

    public StringMessage(String message, int accumulator) {
        super(MESSAGE_PREFIX, message);
        this.accumulatorCount = accumulator;
    }

    private String getAccumulatedContent() {
        if (accumulatorCount == 1) return getContent();
        else { return  String.format("%s (x%s)", getContent(), accumulatorCount); }
    }

    @Override
    public String getDecoratedContent() {
        return decorate(getAccumulatedContent());
    }

    @Override
    public boolean isAccumulatable(Message message) {
        return message instanceof StringMessage && getContent().equals(message.getContent());
    }

    @Override
    public Message accumulate(Message message) {
        return new StringMessage(getContent(), accumulatorCount + ((StringMessage)message).accumulatorCount);
    }
}
