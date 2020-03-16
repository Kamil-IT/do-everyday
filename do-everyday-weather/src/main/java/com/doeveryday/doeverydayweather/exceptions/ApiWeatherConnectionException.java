package com.doeveryday.doeverydayweather.exceptions;

public class ApiWeatherConnectionException extends RuntimeException {
    public ApiWeatherConnectionException() {
    }

    public ApiWeatherConnectionException(String message) {
        super(message);
    }

    public ApiWeatherConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiWeatherConnectionException(Throwable cause) {
        super(cause);
    }

    public ApiWeatherConnectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
