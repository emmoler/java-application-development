package com.acme.dbo.txlog.printer;

public class ConsoleSaver implements Saver {

    public void save(String message) {
        System.out.print(message);
    }
}