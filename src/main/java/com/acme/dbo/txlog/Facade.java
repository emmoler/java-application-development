package com.acme.dbo.txlog;

import com.acme.dbo.txlog.decorator.MessageDecorator;

import static com.acme.dbo.txlog.Constants.*;
import static com.acme.dbo.txlog.printer.ConsolePrinter.print;
import static java.lang.System.lineSeparator;

public class Facade {

    private static final String NO_INPUT = "NO_INPUT";

    private static int ACCUMULATOR_INT = 0;
    private static int ACCUMULATOR_STRING_COUNT = 0;
    private static String ACCUMULATOR_LAST_STRING = "";
    private static String PREVIOUS_JAVA_LANG_TYPE = "";
    private static String CONCATENATED_LOGGER_CONTENT = "";

    private static LoggerState LOGGER_STATE;
    private enum LoggerState {
        IntegerAccumulation,
        ByteAccumulation,
        StringAccumulation
    }

    public static void log(Object obj) { appendLogValue(obj); }
    public static void flush() { print(getLoggerContent()); }

    public static String getLoggerContent() {
        appendLogValue(null, NO_INPUT);
        String loggerContent = CONCATENATED_LOGGER_CONTENT;
        clearMemoryLoggerContent();
        clearMemoryInt();
        clearMemoryString();
        return loggerContent;
    }

    public static void appendLogValue(Object obj) {
        appendLogValue(obj, obj.getClass().getName());
    }

    private static void appendLogValue(Object obj, String currentLogObjType) {

        if (PREVIOUS_JAVA_LANG_TYPE.equals(JAVA_LANG_INTEGER) & !currentLogObjType.equals(JAVA_LANG_INTEGER)) {
            appendIntegerLogLine();
            clearMemoryInt();
        }

        if (PREVIOUS_JAVA_LANG_TYPE.equals(JAVA_LANG_BYTE) & !currentLogObjType.equals(JAVA_LANG_BYTE)) {
            appendIntegerLogLine();
            clearMemoryInt();
        }

        if ( (PREVIOUS_JAVA_LANG_TYPE.equals(JAVA_LANG_STRING) & !currentLogObjType.equals(JAVA_LANG_STRING)) ||
             (PREVIOUS_JAVA_LANG_TYPE.equals(JAVA_LANG_STRING) & currentLogObjType.equals(JAVA_LANG_STRING) && !ACCUMULATOR_LAST_STRING.equals(obj.toString()))) {
            appendStringLogLine();
            clearMemoryString();
        }

        PREVIOUS_JAVA_LANG_TYPE = currentLogObjType;

        switch (currentLogObjType)
        {
            case JAVA_LANG_INTEGER:
                accumulateNewIntegerOrOverflowString((int)obj);
                break;
            case JAVA_LANG_BYTE:
                accumulateNewByteOrOverflowString((byte)obj);
                break;
            case JAVA_LANG_STRING:
                accumulateNewString(obj.toString());
                break;
            case NO_INPUT:
                break;
            default:
                appendCommonLogLine(obj);
        }
    }

    private static void accumulateNewString(String string) {
        ACCUMULATOR_STRING_COUNT++;
        ACCUMULATOR_LAST_STRING = string;
        LOGGER_STATE = LoggerState.StringAccumulation;
    }

    private static void accumulateNewIntegerOrOverflowString(int newNumber) {
        if (ACCUMULATOR_INT > 0 && newNumber > Integer.MAX_VALUE - ACCUMULATOR_INT) {
            PREVIOUS_JAVA_LANG_TYPE = JAVA_LANG_STRING;
            accumulateNewString("Integer.MAX_VALUE");
        } else if (ACCUMULATOR_INT < 0 && newNumber < Integer.MIN_VALUE - ACCUMULATOR_INT) {
            PREVIOUS_JAVA_LANG_TYPE = JAVA_LANG_STRING;
            accumulateNewString("Integer.MIN_VALUE");
        }
        else
        {
            ACCUMULATOR_INT += newNumber;
            LOGGER_STATE = LoggerState.IntegerAccumulation;
        }
    }

    private static void accumulateNewByteOrOverflowString(byte newNumber) {
        if (ACCUMULATOR_INT > 0 && newNumber > Byte.MAX_VALUE - ACCUMULATOR_INT) {
            PREVIOUS_JAVA_LANG_TYPE = JAVA_LANG_STRING;
            accumulateNewString("Byte.MAX_VALUE");
        } else if (ACCUMULATOR_INT < 0 && newNumber < Byte.MIN_VALUE - ACCUMULATOR_INT) {
            PREVIOUS_JAVA_LANG_TYPE = JAVA_LANG_STRING;
            accumulateNewString("Byte.MIN_VALUE");
        }
        else
        {
            ACCUMULATOR_INT += newNumber;
            LOGGER_STATE = LoggerState.ByteAccumulation;
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

    private static void appendCommonLogLine(Object obj) {
        appendLogLine(MessageDecorator.getPrefix(obj) + obj.toString());
    }

    private static void appendLogLine(String message) {
        CONCATENATED_LOGGER_CONTENT = CONCATENATED_LOGGER_CONTENT + message + lineSeparator();
    }

    private static void clearMemoryLoggerContent() {
        CONCATENATED_LOGGER_CONTENT = "";
        PREVIOUS_JAVA_LANG_TYPE = "";
    }

    private static void clearMemoryString() {
        ACCUMULATOR_LAST_STRING = "";
        ACCUMULATOR_STRING_COUNT = 0;
    }

    private static void clearMemoryInt() {
        ACCUMULATOR_INT = 0;
    }
}