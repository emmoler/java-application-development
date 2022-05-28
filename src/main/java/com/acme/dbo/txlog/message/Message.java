package com.acme.dbo.txlog.message;

public interface Message {
    MessageType getMessageType();
    String getContent();
    boolean canAccumulate(Message message);
    void accumulate(Message message);
    String getDecoratedContent();
}
