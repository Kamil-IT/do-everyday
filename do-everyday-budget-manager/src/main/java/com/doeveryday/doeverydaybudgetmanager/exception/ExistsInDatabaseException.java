package com.doeveryday.doeverydaybudgetmanager.exception;

public class ExistsInDatabaseException extends RuntimeException {
    public ExistsInDatabaseException() {
    }

    public ExistsInDatabaseException(String message) {
        super(message);
    }

    public ExistsInDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistsInDatabaseException(Throwable cause) {
        super(cause);
    }

    public ExistsInDatabaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
