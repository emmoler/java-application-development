package com.acme.dbo.txlog.printer;

import static java.lang.System.lineSeparator;

public class ConsoleSaver implements Saver {

    public void save(String message) {
        System.out.print(message  + lineSeparator());
    }
}