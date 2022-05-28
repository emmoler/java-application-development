package com.acme.dbo.txlog.message;

import com.acme.dbo.txlog.service.LoggerState;

public class ObjectMessage extends DecoratedMessage {

    private static final String MESSAGE_PREFIX = "reference: ";
    private String CONTENT;

    public ObjectMessage(Object message) {
        super(MESSAGE_PREFIX);
        CONTENT = String.valueOf(message);
    }

    @Override
    public String getContent() {
        return String.valueOf(CONTENT);
    }

    @Override
    public LoggerState getState() {
        return LoggerState.OBJ;
    }
}
