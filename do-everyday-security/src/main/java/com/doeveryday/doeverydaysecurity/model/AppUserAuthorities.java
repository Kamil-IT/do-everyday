package com.doeveryday.doeverydaysecurity.model;

public enum AppUserAuthorities {
    /**
     * Budget manager
     */
//    Admin - for all data
    BUDGET_GET("budget:get"),
    BUDGET_UPDATE("budget:update"),
    BUDGET_CREATE("budget:create"),
    BUDGET_DELETE("budget:delete"),
//    User - for data with user create
    BUDGET_GET_CREATOR("budget:getcreator"),
    BUDGET_UPDATE_CREATOR("budget:updatecreator"),
    BUDGET_CREATE_CREATOR("budget:createcreator"),
    BUDGET_DELETE_CREATOR("budget:deletecreator"),

    /**
     * Data about user
     */
//    Admin - for all data
    USER_ALL_DETAILS_GET("user:details:get"),
    USER_ALL_DETAILS_UPDATE("user:details:update"),
    USER_ALL_DETAILS_CREATE("user:details:create"),
    USER_ALL_DETAILS_DELETE("user:details:delete"),
//    User - for data with user create
    USER_ALL_DETAILS_GET_CREATOR("user:details:getcreator"),
    USER_ALL_DETAILS_UPDATE_CREATOR("user:details:updatecreator"),
    USER_ALL_DETAILS_CREATE_CREATOR("user:details:createcreator"),
    USER_ALL_DETAILS_DELETE_CREATOR("user:details:deletecreator");

    private final String permission;

    AppUserAuthorities(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
