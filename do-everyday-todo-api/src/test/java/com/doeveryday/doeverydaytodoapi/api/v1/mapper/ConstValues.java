package com.doeveryday.doeverydaytodoapi.api.v1.mapper;

import com.doeveryday.doeverydaysecurity.model.AppUser;
import com.doeveryday.doeverydaytodo.models.Priority;

import java.util.Date;
import java.util.UUID;

public class ConstValues {
    //    Board values
    public static final long BOARD_ID = 1L;
    public static final String COLOR_HEX = "#ffffff";
    public static final String BOARD_NAME = "Shop list";
    public static final AppUser APP_USER = AppUser.builder().id(UUID.randomUUID().toString()).build();
    //    Task values
    public static final long TASK_ID = 2L;
    public static final Date TASK_CREATE_DATE = new Date();
    public static final String DESCRIPTION_TASK = "Example task";
    public static final String TASK_NAME = "Task name";
    public static final long TASK_MANAGER_ID = 3L;
//    Priority values
    public static final Priority IMPORTANT = Priority.IMPORTANT;
//    Task member values
    public static final Long TASK_MEMBER_ID = 4L;
    public static final Boolean TASK_MEMBER_IS_DONE = true;

}