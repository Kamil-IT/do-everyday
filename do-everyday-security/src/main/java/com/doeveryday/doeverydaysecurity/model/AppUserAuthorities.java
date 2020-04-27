package com.doeveryday.doeverydaysecurity.model;

public enum AppUserAuthorities {
    /**
     * Budget manager
     */
//    Admin - access for all data
    BUDGET_ADMIN_READ("read:admin::budget"),
    BUDGET_ADMIN_WRITE("write:admin::budget"),

//    TODO:DELETE THIS(redundant)
    BUDGET_UPDATE("budget:update"),
    BUDGET_DELETE("budget:delete"),

//    User - access for data with user create
    BUDGET_GET("read::budget"),
    BUDGET_WRITE("write:admin::budget"),

    //    TODO:DELETE THIS(redundant)
    BUDGET_UPDATE_CREATOR("budget:updatecreator"),
    BUDGET_DELETE_CREATOR("budget:deletecreator"),

    /**
     * Data about user
     */
//    Admin - for all data
    USER_DETAILS_ADMIN_GET("read:admin::details"),
    USER_DETAILS_ADMIN_WRITE("write:admin::details"),

    //    TODO:DELETE THIS(redundant)
    USER_ALL_DETAILS_UPDATE("user:details:update"),
    USER_ALL_DETAILS_DELETE("user:details:delete"),

//    User - for data with user create
    USER_DETAILS_READ("read::details"),
    USER_DETAILS_WRITE("write::details"),

    //    TODO:DELETE THIS(redundant)
    USER_ALL_DETAILS_UPDATE_CREATOR("user:details:updatecreator"),
    USER_ALL_DETAILS_DELETE_CREATOR("user:details:deletecreator");

    private final String permission;

    AppUserAuthorities(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
