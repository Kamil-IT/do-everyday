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
    BUDGET_DELETE_CREATOR("budget:deletecreator");

    private final String permission;

    AppUserAuthorities(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
