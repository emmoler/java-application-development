package com.acme.dbo.txlog.message;

public class IntMessage extends DecoratedMessage{

    private static final String MESSAGE_PREFIX = "primitive: ";
    private static final String OVERFLOW_UPPER_BOUND_MESSAGE = "Integer.MAX_VALUE";
    private static final String OVERFLOW_LOWER_BOUND_MESSAGE = "Integer.MIN_VALUE";

    private final int accumulator;

    public IntMessage(int message) {
        super(MESSAGE_PREFIX, String.valueOf(message));
        accumulator = message;
    }

    @Override
    public String getContent() {
        return String.valueOf(accumulator);
    }

    private String getOverflownAccumulator() {
        switch (accumulator) {
            case Integer.MAX_VALUE:
                return OVERFLOW_UPPER_BOUND_MESSAGE;
            case Integer.MIN_VALUE:
                return OVERFLOW_LOWER_BOUND_MESSAGE;
            default :
                return String.valueOf(accumulator);
        }
    }

    @Override
    public String getDecoratedContent() {
        return decorate(getOverflownAccumulator());
    }

    @Override
    public boolean isAccumulatable(Message message) {
        if (message instanceof IntMessage)
        {
            int newNumber = ((IntMessage)message).accumulator;
            if (accumulator > 0 && newNumber > Integer.MAX_VALUE - accumulator) {
                return false;
            } else if (accumulator < 0 && newNumber < Integer.MIN_VALUE - accumulator) {
                return false;
            }
            else
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public Message accumulate(Message message) {
        return new IntMessage(accumulator + ((IntMessage)message).accumulator);
    }
}