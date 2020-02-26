package com.doeveryday.doeverydaytodo.service;

import com.doeveryday.doeverydaytodo.models.Board;
import com.doeveryday.doeverydaytodo.models.Task;

import java.util.List;

public interface TaskService {
    Task saveTask(Task task);

    List<Task> getTasks();

    Task findById(Long id);

    void deleteById(Long id);

    void updateTask(Task task);

    boolean existsById(Long id);
}
