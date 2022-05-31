package com.acme.dbo.txlog.message;

public interface Message {
    MessageType getMessageType();
    String getContent();
    boolean isAccumulatable(Message message);
    Message accumulate(Message message);
    String getDecoratedContent();
}
