package com.doeveryday.doeverydayweb.exceptions;

public class NotFoundExceptions extends RuntimeException{
    public NotFoundExceptions() {
    }

    public NotFoundExceptions(String message) {
        super(message);
    }

    public NotFoundExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundExceptions(Throwable cause) {
        super(cause);
    }

    public NotFoundExceptions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
