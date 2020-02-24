package com.doeveryday.doeverydaytodo.repository;

import com.doeveryday.doeverydaytodo.models.TaskManager;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskManagerRepository extends CrudRepository<TaskManager, Long> {

    List<TaskManager> findByDoneIsTrue();

    List<TaskManager> findByDoneIsFalse();
}
