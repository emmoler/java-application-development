package com.acme.dbo.txlog.message;

import com.acme.dbo.txlog.service.LoggerState;

import static java.lang.System.lineSeparator;

public class ByteMessage extends DecoratedMessage {

    private static final String MESSAGE_PREFIX = "primitive: ";
    private int ACCUMULATOR = 0;

    public ByteMessage(byte message) {
        super(MESSAGE_PREFIX);
        ACCUMULATOR = message;
    }

    @Override
    public LoggerState getState() {
        return LoggerState.BYTE;
    }

    @Override
    public String getContent() {
        return String.valueOf(ACCUMULATOR);
    }

    @Override
    public String getDecoratedContent() {
        String retVal;
        switch (ACCUMULATOR) {
            case Byte.MAX_VALUE:
                retVal = "Byte.MAX_VALUE";
                break;
            case Byte.MIN_VALUE:
                retVal = "Byte.MIN_VALUE";
                break;
            default :
                retVal = String.valueOf(ACCUMULATOR);
        }
        return MESSAGE_PREFIX + retVal + lineSeparator();
    }

    @Override
    public boolean canAccumulate(Message m) {
        return false;
    }
}