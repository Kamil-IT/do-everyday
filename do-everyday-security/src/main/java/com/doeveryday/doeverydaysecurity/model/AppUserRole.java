package com.doeveryday.doeverydaysecurity.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.doeveryday.doeverydaysecurity.model.AppUserAuthorities.*;

public enum AppUserRole {
    /**
     * Have authority to read, add, modify stuff they add on their own
     */
    USER(new HashSet<>(Arrays.asList(
//            Budget manager
            BUDGET_GET_CREATOR,
            BUDGET_CREATE_CREATOR,
            BUDGET_UPDATE_CREATOR,
            BUDGET_DELETE_CREATOR,

//            User details
            USER_ALL_DETAILS_GET_CREATOR,
            USER_ALL_DETAILS_UPDATE_CREATOR,
            USER_ALL_DETAILS_CREATE_CREATOR,
            USER_ALL_DETAILS_DELETE_CREATOR))),
    /**
     * Have authority to read, add and modify all stuff
     */
    EMPLOYEE(new HashSet<>(Arrays.asList(
//            Budget manager
            BUDGET_GET,
            BUDGET_CREATE,
            BUDGET_UPDATE,

//            User details
            USER_ALL_DETAILS_GET,
            USER_ALL_DETAILS_CREATE,
            USER_ALL_DETAILS_UPDATE))),
    /**
     * Have authority to read, add, modify, and delete all stuff
     */
    ADMIN(new HashSet<>(Arrays.asList(
//            Budget manager
            BUDGET_GET,
            BUDGET_CREATE,
            BUDGET_UPDATE,
            BUDGET_DELETE,

//            User details
            USER_ALL_DETAILS_GET,
            USER_ALL_DETAILS_CREATE,
            USER_ALL_DETAILS_UPDATE,
            USER_ALL_DETAILS_DELETE)));

    private final Set<AppUserAuthorities> authorities;

    AppUserRole(HashSet<AppUserAuthorities> authorities) {
        this.authorities = authorities;
    }

    public Set<GrantedAuthority> getAuthorities(){
        Set<GrantedAuthority> grantedAuthorities = authorities.stream()
                .map(appUserAuthorities ->
                        new SimpleGrantedAuthority(appUserAuthorities.getPermission()))
                .collect(Collectors.toSet());
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return grantedAuthorities;
    }
}
