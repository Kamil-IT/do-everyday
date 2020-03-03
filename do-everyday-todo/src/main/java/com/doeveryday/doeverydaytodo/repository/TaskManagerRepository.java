package com.doeveryday.doeverydaytodo.repository;

import com.doeveryday.doeverydaytodo.models.Task;
import com.doeveryday.doeverydaytodo.models.TaskManager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskManagerRepository extends CrudRepository<TaskManager, Long> {
    TaskManager findByTask(Task task);
}
