package com.doeveryday.doeverydaytodoapi.services;

import com.doeveryday.doeverydaytodoapi.api.v1.model.TaskDTO;

import java.util.List;

public interface TaskApiService {

    List<TaskDTO> getAllTasks();

    TaskDTO getTaskById(Long id);

    TaskDTO createTask(TaskDTO taskDTO);

    void deleteTaskById(Long id);

    TaskDTO putTask(TaskDTO taskDTO, Long id);

    List<TaskDTO> getTaskByBoardId(Long boardId);
}
