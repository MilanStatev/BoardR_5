package com.loggers;

import com.loggers.Logger;

public class ConsoleLogger implements Logger {

    @Override
    public void log(String value) {
        System.out.println(value);
    }
}
