package com.acme.dbo.txlog.message;

import com.acme.dbo.txlog.service.LoggerState;

public class EmptyMessage extends DecoratedMessage {

    public EmptyMessage() {
        super("");
    }

    @Override
    public LoggerState getState() {
        return LoggerState.NA;
    }

    @Override
    public String getContent() {
        return "";
    }

    @Override
    public boolean canAccumulate(Message m) {
        return false;
    }
}
