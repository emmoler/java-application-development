package com.acme.dbo.txlog.service;

import com.acme.dbo.txlog.message.EmptyMessage;
import com.acme.dbo.txlog.message.Message;
import com.acme.dbo.txlog.printer.Saver;

public class LogService {

    private Message lastMessage = new EmptyMessage();
    private Saver saver;

    public LogService (Saver saver) {
        this.saver = saver;
    }

    public void log(Message message) {
        if (lastMessage.canAccumulate(message)) {
            lastMessage.accumulate(message);
        }
        else {
            saver.save(lastMessage.getDecoratedContent());
            lastMessage = message;
        }
    }
}
