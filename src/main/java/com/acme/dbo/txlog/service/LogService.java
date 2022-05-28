package com.acme.dbo.txlog.service;

import com.acme.dbo.txlog.message.Message;
import com.acme.dbo.txlog.printer.ConsolePrinter;

public class LogService {

    private Message previousMessage = null;
    private LoggerState loggerState = LoggerState.NA;

    private final ConsolePrinter printer = new ConsolePrinter();

    public void log(Message message) {

        if (loggerState != LoggerState.NA && previousMessage.canAccumulate(message)) {
            previousMessage.accumulate(message);
        }
        else
        {
            flush();
            previousMessage = message;
            loggerState = previousMessage.getState();
        }
    }

    public void flush() {
        if (loggerState != LoggerState.NA)
            printer.print(previousMessage.getDecoratedContent());
        loggerState = LoggerState.NA;
    }
}
