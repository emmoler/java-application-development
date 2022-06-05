package com.acme.dbo.txlog.service;

import com.acme.dbo.txlog.saver.SaverException;

public class LogOperationException extends RuntimeException  {
    public LogOperationException(String message, SaverException e) {
        super(message, e);
    }
}
