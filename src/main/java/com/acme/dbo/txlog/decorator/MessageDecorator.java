package com.acme.dbo.txlog.decorator;

import com.acme.dbo.txlog.Constants;

public class MessageDecorator {

    private static final String MESSAGE_PREFIX_PRIMITIVE = "primitive: ";
    private static final String MESSAGE_PREFIX_CHAR = "char: ";
    private static final String MESSAGE_PREFIX_STRING = "string: ";
    private static final String MESSAGE_PREFIX_REFERENCE = "reference: ";
    private static final String STRING_EMPTY = "";

    public static String getPrefix(Object obj) {
        switch (obj.getClass().getName())
        {
            case Constants.JAVA_LANG_INTEGER:
            case Constants.JAVA_LANG_BOOLEAN:
            case Constants.JAVA_LANG_BYTE:
                return MESSAGE_PREFIX_PRIMITIVE;
            case Constants.JAVA_LANG_CHARACTER:
                return MESSAGE_PREFIX_CHAR;
            case Constants.JAVA_LANG_STRING:
                return MESSAGE_PREFIX_STRING;
            case Constants.JAVA_LANG_OBJECT:
                return MESSAGE_PREFIX_REFERENCE;
            default:
                return STRING_EMPTY;
        }
    }
}