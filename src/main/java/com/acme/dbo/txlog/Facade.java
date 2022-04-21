package com.acme.dbo.txlog;

import com.acme.dbo.txlog.decorator.MessageDecorator;

import static com.acme.dbo.txlog.Constants.*;
import static com.acme.dbo.txlog.printer.ConsolePrinter.print;
import static java.lang.System.lineSeparator;

public class Facade {

    private static final String NO_NEW_INPUT = "";

    private static int ACCUMULATOR_INT = 0;
    private static int ACCUMULATOR_STRING_COUNT = 0;
    private static String ACCUMULATOR_LAST_STRING = "";
    private static String CONCATENATED_LOGGER_CONTENT = "";

    private static Object currentLogObject;

    private static AccumulationType ACCUMULATION_TYPE = AccumulationType.Other;
    private enum AccumulationType {
        Integer,
        Byte,
        String,
        Other
    }

    public static void log(Object obj) {
        currentLogObject = obj;
        appendAccumulatedLines();
        accumulateAdditionalLine();
        appendCommonLogLine();
    }

    public static void flush() {
        currentLogObject = null;
        appendAccumulatedLines();
        clearMemory();
    }

    private static void accumulateAdditionalLine() {
        switch (currentLogObject.getClass().getName())
        {
            case JAVA_LANG_INTEGER:
                accumulateNewIntegerOrOverflowString((int)currentLogObject);
                break;
            case JAVA_LANG_BYTE:
                accumulateNewByteOrOverflowString((byte)currentLogObject);
                break;
            case JAVA_LANG_STRING:
                accumulateNewString(currentLogObject.toString());
                break;
        }
    }

    private static void appendAccumulatedLines() {
        String currentLogObjType = NO_NEW_INPUT;
        if (!(currentLogObject ==null)) currentLogObjType = currentLogObject.getClass().getName();

        if (ACCUMULATION_TYPE == AccumulationType.Integer & !currentLogObjType.equals(JAVA_LANG_INTEGER)) {
            appendIntegerLogLine();
        }

        if (ACCUMULATION_TYPE == AccumulationType.Byte & !currentLogObjType.equals(JAVA_LANG_BYTE)) {
            appendByteLogLine();
        }

        if (ACCUMULATION_TYPE == AccumulationType.String & !currentLogObjType.equals(JAVA_LANG_STRING))  {
            appendStringLogLine();
        }

        if (ACCUMULATION_TYPE == AccumulationType.String & currentLogObjType.equals(JAVA_LANG_STRING) && !ACCUMULATOR_LAST_STRING.equals(currentLogObject.toString())) {
            appendStringLogLine();
        }
    }

    private static void accumulateNewString(String string) {
        ACCUMULATOR_STRING_COUNT++;
        ACCUMULATOR_LAST_STRING = string;
        ACCUMULATION_TYPE = AccumulationType.String;
    }

    private static void accumulateNewIntegerOrOverflowString(int newNumber) {
        if (ACCUMULATOR_INT > 0 && newNumber > Integer.MAX_VALUE - ACCUMULATOR_INT) {
            accumulateNewString("Integer.MAX_VALUE");
        } else if (ACCUMULATOR_INT < 0 && newNumber < Integer.MIN_VALUE - ACCUMULATOR_INT) {
            accumulateNewString("Integer.MIN_VALUE");
        }
        else
        {
            ACCUMULATOR_INT += newNumber;
            ACCUMULATION_TYPE = AccumulationType.Integer;
        }
    }

    private static void accumulateNewByteOrOverflowString(byte newNumber) {
        if (ACCUMULATOR_INT > 0 && newNumber > Byte.MAX_VALUE - ACCUMULATOR_INT) {
            accumulateNewString("Byte.MAX_VALUE");
        } else if (ACCUMULATOR_INT < 0 && newNumber < Byte.MIN_VALUE - ACCUMULATOR_INT) {
            accumulateNewString("Byte.MIN_VALUE");
        }
        else
        {
            ACCUMULATOR_INT += newNumber;
            ACCUMULATION_TYPE = AccumulationType.Byte;
        }
    }

    private static void appendStringLogLine() {
        final String formatCounterPostfix = " (x%s)";
        String postfix = "";
        if (ACCUMULATOR_STRING_COUNT > 1) {
            postfix = String.format(formatCounterPostfix, ACCUMULATOR_STRING_COUNT);
        }
        appendLogLine(MessageDecorator.getPrefix(ACCUMULATOR_LAST_STRING) + ACCUMULATOR_LAST_STRING + postfix);
    }

    private static void appendIntegerLogLine() {
        appendLogLine(MessageDecorator.getPrefix(ACCUMULATOR_INT) + ACCUMULATOR_INT);
    }

    private static void appendByteLogLine() {
        appendLogLine(MessageDecorator.getPrefix(ACCUMULATOR_INT) + ACCUMULATOR_INT);
    }

    private static void appendCommonLogLine() {
        if (ACCUMULATION_TYPE == AccumulationType.Other)
        appendLogLine(MessageDecorator.getPrefix(currentLogObject) + currentLogObject.toString());
    }

    private static void appendLogLine(String message) {
        print(CONCATENATED_LOGGER_CONTENT += message + lineSeparator());
        clearMemory();
    }

    private static void clearMemory() {
        switch (ACCUMULATION_TYPE)
        {
            case Other:
                CONCATENATED_LOGGER_CONTENT = "";
                ACCUMULATOR_INT = 0;
                ACCUMULATOR_LAST_STRING = "";
                ACCUMULATOR_STRING_COUNT = 0;
                break;
            case Integer:
            case Byte:
                ACCUMULATOR_INT = 0;
                break;
            case String:
                ACCUMULATOR_LAST_STRING = "";
                ACCUMULATOR_STRING_COUNT = 0;
                break;
        }
        ACCUMULATION_TYPE = AccumulationType.Other;
    }
}