package com.pranjalkaler.server.utils;

import java.sql.Timestamp;

public class Logger {

    private final Class<?> loggingClass;

    private static final String DEBUG = "DEBUG";
    private static final String ERROR = "ERROR";

    public Logger(Class<?> loggingClass) {
        this.loggingClass = loggingClass;
    }

    public void debug(String log) {
        this.print(DEBUG, log);
    }

    public void error(String log) {
        this.print(ERROR, log);
    }

    private void print(String type, String logMessage) {
        String log = String.format("%s : [ %s ] : %s : %s", getTimestamp(), type, loggingClass.getName(), logMessage);
        System.out.println(log);
    }

    private String getTimestamp() {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        return currentTimestamp.toString();
    }
}
