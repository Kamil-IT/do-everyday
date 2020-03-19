package com.doeveryday.doeverydaytodo.repository;

import com.doeveryday.doeverydaytodo.models.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findAllByBoardId(Long boardId);
}
