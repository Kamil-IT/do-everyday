package com.doeveryday.doeverydaytodo.repository;

import com.doeveryday.doeverydaytodo.models.TaskManager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskManagerRepository extends CrudRepository<TaskManager, Long> {

    List<TaskManager> findByDoneIsTrue();

    List<TaskManager> findByDoneIsFalse();
}
