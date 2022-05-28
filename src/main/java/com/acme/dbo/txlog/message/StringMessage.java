package com.acme.dbo.txlog.message;

import static java.lang.System.lineSeparator;


public class StringMessage extends DecoratedMessage {

    private static final String MESSAGE_PREFIX = "string: ";
    private int ACCUMULATOR_COUNT = 0;
    private String ACCUMULATOR = "";

    public StringMessage(String message) {
        super(MESSAGE_PREFIX);
        ACCUMULATOR_COUNT++;
        ACCUMULATOR = message;
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.STRING;
    }

    @Override
    public String getContent() {
        return ACCUMULATOR;
    }

    @Override
    public String getDecoratedContent() {
        String retVal;
        if (ACCUMULATOR_COUNT == 1) retVal = ACCUMULATOR;
        else { retVal =  String.format("%s (x%s)", ACCUMULATOR, ACCUMULATOR_COUNT); }
        return MESSAGE_PREFIX + retVal + lineSeparator();
    }

    @Override
    public boolean canAccumulate(Message message) {
        if (!this.getContent().equals(message.getContent()))  {
            return false;
        }
        return true;
    }

    @Override
    public void accumulate(Message message) {
        ACCUMULATOR_COUNT++;
    }
}
