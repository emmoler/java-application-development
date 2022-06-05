package com.acme.dbo.txlog.service;

import com.acme.dbo.txlog.message.EmptyMessage;
import com.acme.dbo.txlog.message.Message;
import com.acme.dbo.txlog.saver.Saver;
import com.acme.dbo.txlog.saver.SaverException;

public class LogService {

    private Message lastMessage = new EmptyMessage();
    private final Saver saver;

    public LogService (Saver saver) {
        this.saver = saver;
    }

    public void log(Message message) throws LogOperationException {
        if (lastMessage.isAccumulatable(message)) {
            lastMessage = lastMessage.accumulate(message);
        }
        else {
            try {
                saver.save(lastMessage.getDecoratedContent());
            }
            catch (SaverException e){
                throw new LogOperationException(e.getMessage(), e);
            }
            lastMessage = message;
        }
    }
}