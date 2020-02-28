package com.doeveryday.doeverydaytodo.service;

import com.doeveryday.doeverydaytodo.models.TaskManager;

import java.util.List;

public interface TaskManagerService {
    TaskManager saveTaskManager(TaskManager taskManager);

    List<TaskManager> getTaskManager();

    TaskManager findById(Long id);

    void deleteById(Long id);

    void updateTaskManager(TaskManager taskManager);
}
