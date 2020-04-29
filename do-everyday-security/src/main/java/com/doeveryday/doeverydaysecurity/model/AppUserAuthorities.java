package com.doeveryday.doeverydaysecurity.model;

public enum AppUserAuthorities {
    /**
     * Budget manager
     */
//    Admin - access for all data
    BUDGET_ADMIN_READ("read:admin::budget"),
    BUDGET_ADMIN_WRITE("write:admin::budget"),

//    User - access for data with user create
    BUDGET_GET("read::budget"),
    BUDGET_WRITE("write:admin::budget"),

    /**
     * Data about user
     */
//    Admin - for all data
    USER_DETAILS_ADMIN_GET("read:admin::details"),
    USER_DETAILS_ADMIN_WRITE("write:admin::details"),

//    User - for data with user create
    USER_DETAILS_READ("read::details"),
    USER_DETAILS_WRITE("write::details");

    private final String permission;

    AppUserAuthorities(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
