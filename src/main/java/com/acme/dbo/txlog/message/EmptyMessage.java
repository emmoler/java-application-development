package com.acme.dbo.txlog.message;

public class EmptyMessage extends DecoratedMessage {

    public EmptyMessage() {
        super("");
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.NA;
    }

    @Override
    public String getContent() {
        return "";
    }

    @Override
    public boolean canAccumulate(Message message) {
        return false;
    }
}
