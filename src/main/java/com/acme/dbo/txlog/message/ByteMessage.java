package com.acme.dbo.txlog.message;

public class ByteMessage extends DecoratedMessage {

    private static final String MESSAGE_PREFIX = "primitive: ";
    private static final String OVERFLOW_UPPER_BOUND_MESSAGE = "Byte.MAX_VALUE";
    private static final String OVERFLOW_LOWER_BOUND_MESSAGE = "Byte.MIN_VALUE";

    private int ACCUMULATOR = 0;

    public ByteMessage(byte message) {
        super(MESSAGE_PREFIX, MessageType.BYTE);
        ACCUMULATOR = message;
    }

    @Override
    public String getContent() {
        return String.valueOf(ACCUMULATOR);
    }

    @Override
    public boolean isAccumulatable(Message message) {
        return false;
    }

    @Override
    public Message accumulate(Message message) {

        return message;
    }

    @Override
    public String getDecoratedContent() {
        String retVal;
        switch (ACCUMULATOR) {
            case Byte.MAX_VALUE:
                retVal = OVERFLOW_UPPER_BOUND_MESSAGE;
                break;
            case Byte.MIN_VALUE:
                retVal = OVERFLOW_LOWER_BOUND_MESSAGE;
                break;
            default :
                retVal = String.valueOf(ACCUMULATOR);
        }
        return decorate(retVal);
    }
}