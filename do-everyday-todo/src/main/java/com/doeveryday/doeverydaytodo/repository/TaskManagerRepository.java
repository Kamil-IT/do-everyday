package com.doeveryday.doeverydaytodo.repository;

import com.doeveryday.doeverydaytodo.models.Task;
import com.doeveryday.doeverydaytodo.models.TaskManager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskManagerRepository extends CrudRepository<TaskManager, Long> {

    Optional<TaskManager> findByTask(Task task);

    Optional<TaskManager> findFirstByTask_Id(Long task_id);
}
