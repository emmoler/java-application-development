package com.acme.dbo.txlog.message;

public abstract class DecoratedMessage implements Message {

    private final String messagePrefix;
    protected String stringMessageContent;
    protected MessageType messageType;

    public DecoratedMessage(String prefixMessage, MessageType messageType) {
        messagePrefix = prefixMessage;
        this.messageType = messageType;
    }

    protected String decorate(String content) {
        return messagePrefix + content;
    }

    public String getDecoratedContent() {
        return getMessageType() != MessageType.NA ? decorate(getContent()) : "";
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public String getContent() {
        return String.valueOf(stringMessageContent);
    }

}
