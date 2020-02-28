package com.doeveryday.doeverydayweather.exceptions;

public class NullPointerException extends RuntimeException {
    public NullPointerException(){
        super();
    }

    public NullPointerException(String message){
        super(message);
    }

    public NullPointerException(String message, Throwable cause){
        super(message, cause);
    }
}
