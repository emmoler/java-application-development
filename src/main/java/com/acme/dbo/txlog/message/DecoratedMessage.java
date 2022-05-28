package com.acme.dbo.txlog.message;

import com.acme.dbo.txlog.printer.ConsolePrinter;
import com.acme.dbo.txlog.service.LoggerState;

import static java.lang.System.lineSeparator;

public class DecoratedMessage implements Message {

    private String MESSAGE_PREFIX;

    public DecoratedMessage(String prefixMessage) {
        MESSAGE_PREFIX = prefixMessage;
    }

    public String getDecoratedContent() {
        return MESSAGE_PREFIX + getContent() + lineSeparator();
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
