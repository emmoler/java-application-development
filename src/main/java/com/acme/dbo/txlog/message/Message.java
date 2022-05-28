package com.acme.dbo.txlog.message;

import com.acme.dbo.txlog.service.LoggerState;

public interface Message {
    LoggerState getState();
    String getContent();
    boolean canAccumulate(Message m);
    void accumulate(Message message);
    String getDecoratedContent();
}
