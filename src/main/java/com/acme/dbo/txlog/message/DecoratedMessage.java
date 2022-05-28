package com.acme.dbo.txlog.message;

import com.acme.dbo.txlog.service.LoggerState;

import static java.lang.System.lineSeparator;

public class DecoratedMessage implements Message {

    private String MESSAGE_PREFIX;

    public DecoratedMessage(String prefixMessage) {
        MESSAGE_PREFIX = prefixMessage;
    }

    public String getDecoratedContent() {
        if (!getContent().isEmpty())
            return MESSAGE_PREFIX + getContent() + lineSeparator();
        else
            return "";
    }

    public LoggerState getState() {
        return LoggerState.NA;
    }


    public String getContent() {
        throw new java.lang.UnsupportedOperationException("Not supported");
    }


    public boolean canAccumulate(Message m) {
        return false;
    }


    public void accumulate(Message message) {
        throw new java.lang.UnsupportedOperationException("Not supported");
    }
}
