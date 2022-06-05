package com.acme.dbo.txlog.saver;

import static java.lang.System.lineSeparator;

public class ConsoleSaver implements Saver {

    public void save(String message) throws SaverException {
        if (message.contains("MyTestException")) throw new SaverException(); // for exception testing
        if (message.equals("")) return;
        System.out.print(message  + lineSeparator());
    }
}