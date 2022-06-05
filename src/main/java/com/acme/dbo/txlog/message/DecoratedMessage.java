package com.acme.dbo.txlog.message;

public abstract class DecoratedMessage implements Message {

    private final String messagePrefix;
    private final String stringMessageContent;

    public DecoratedMessage(String prefixMessage, String stringMessageContent) {
        messagePrefix = prefixMessage;
        this.stringMessageContent = stringMessageContent;
    }

    protected String decorate(String content) {
        return messagePrefix + content;
    }

    public String getDecoratedContent() {
        return decorate(getContent());
    }

    public String getContent() {
        return String.valueOf(stringMessageContent);
    }
}
