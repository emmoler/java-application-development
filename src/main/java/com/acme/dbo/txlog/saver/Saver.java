package com.acme.dbo.txlog.saver;

public interface Saver {
    void save(String decoratedMessage) throws SaverException;
}