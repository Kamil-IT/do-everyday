package com.doeveryday.doeverydayspotify.exception;

public class SpotifyApiException extends RuntimeException {
    public SpotifyApiException() {
    }

    public SpotifyApiException(String message) {
        super(message);
    }

    public SpotifyApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpotifyApiException(Throwable cause) {
        super(cause);
    }

    public SpotifyApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
