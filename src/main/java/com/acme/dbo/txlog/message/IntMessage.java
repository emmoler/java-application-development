package com.acme.dbo.txlog.message;

public class IntMessage extends DecoratedMessage{

    private static final String MESSAGE_PREFIX = "primitive: ";
    private static final String OVERFLOW_UPPER_BOUND_MESSAGE = "Integer.MAX_VALUE";
    private static final String OVERFLOW_LOWER_BOUND_MESSAGE = "Integer.MIN_VALUE";

    private int accumulator = 0;

    public IntMessage(int message) {
        super(MESSAGE_PREFIX, MessageType.INT);
        accumulator = message;
    }

    @Override
    public String getContent() {
        return String.valueOf(accumulator);
    }

    @Override
    public String getDecoratedContent() {
        String retVal;
        switch (accumulator) {
            case Integer.MAX_VALUE:
                retVal = OVERFLOW_UPPER_BOUND_MESSAGE;
                break;
            case Integer.MIN_VALUE:
                retVal = OVERFLOW_LOWER_BOUND_MESSAGE;
                break;
            default :
                retVal = String.valueOf(accumulator);
        }
        return decorate(retVal);
    }

    @Override
    public boolean isAccumulatable(Message message) {
        if (getMessageType() == message.getMessageType())
        {
            int newNumber = Integer.parseInt(message.getContent());
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
        accumulator += Integer.parseInt(message.getContent());
        return new IntMessage(accumulator);
    }
}