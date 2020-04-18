package com.doeveryday.doeverydayweb.model;

public enum BootstrapAlert {
    DANGER("alert alert-danger"),
    WARRING("alert alert-warning"),
    SUCCESS("alert alert-success");

    private String message;

    BootstrapAlert(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
