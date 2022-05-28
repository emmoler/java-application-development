package com.acme.dbo.txlog.message;

import com.acme.dbo.txlog.service.LoggerState;

import static java.lang.System.lineSeparator;

public class IntMessage extends DecoratedMessage{

    private static final String MESSAGE_PREFIX = "primitive: ";
    private int ACCUMULATOR = 0;

    public IntMessage(int message) {
        super(MESSAGE_PREFIX);
        ACCUMULATOR = message;
    }

    @Override
    public LoggerState getState() {
        return LoggerState.INT;
    }

    @Override
    public String getContent() {
                return String.valueOf(ACCUMULATOR);
    }

    @Override
    public String getDecoratedContent() {
        String retVal;
        switch (ACCUMULATOR) {
            case Integer.MAX_VALUE:
                retVal = "Integer.MAX_VALUE";
                break;
            case Integer.MIN_VALUE:
                retVal = "Integer.MIN_VALUE";
                break;
            default :
                retVal = String.valueOf(ACCUMULATOR);
        }
        return MESSAGE_PREFIX + retVal + lineSeparator();
    }

    @Override
    public boolean canAccumulate(Message m) {
        if (getState() == m.getState())
        {
            int newNumber = Integer.parseInt(m.getContent());
            if (ACCUMULATOR > 0 && newNumber > Integer.MAX_VALUE - ACCUMULATOR) {
                return false;
            } else if (ACCUMULATOR < 0 && newNumber < Integer.MIN_VALUE - ACCUMULATOR) {
                return false;
            }
            else
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public void accumulate(Message message) {
        ACCUMULATOR += Integer.parseInt(message.getContent());
    }
}