package com.acme.dbo.txlog.message;

import com.acme.dbo.txlog.service.LoggerState;

public class CharMessage extends DecoratedMessage {

    private static final String MESSAGE_PREFIX = "char: ";
    private String ACCUMULATOR = "";

    public CharMessage(char message) {
        super(MESSAGE_PREFIX);
        ACCUMULATOR = String.valueOf(message);
    }

    @Override
    public LoggerState getState() {
        return LoggerState.CHAR;
    }

    @Override
    public String getContent() {
        return String.valueOf(ACCUMULATOR);
    }

    @Override
    public boolean canAccumulate(Message m) {
        return false;
    }
}
