package com.acme.dbo.txlog.message;

public class ByteMessage extends DecoratedMessage {

    private static final String MESSAGE_PREFIX = "primitive: ";
    private static final String OVERFLOW_UPPER_BOUND_MESSAGE = "Byte.MAX_VALUE";
    private static final String OVERFLOW_LOWER_BOUND_MESSAGE = "Byte.MIN_VALUE";

    private int accumulator = 0;

    public ByteMessage(byte message) {
        super(MESSAGE_PREFIX, String.valueOf(message));
        accumulator = message;
    }

    @Override
    public String getContent() {
        return String.valueOf(accumulator);
    }

    @Override
    public boolean isAccumulatable(Message message) {
        return false;
    }

    @Override
    public Message accumulate(Message message) {
        throw new java.lang.UnsupportedOperationException("Not supported");
    }

    private String getOverflownAccumulator() {
        switch (accumulator) {
            case Byte.MAX_VALUE:
                return OVERFLOW_UPPER_BOUND_MESSAGE;
            case Byte.MIN_VALUE:
                return OVERFLOW_LOWER_BOUND_MESSAGE;
            default :
                return String.valueOf(accumulator);
        }
    }

    @Override
    public String getDecoratedContent() {
        return decorate(getOverflownAccumulator());
    }
}