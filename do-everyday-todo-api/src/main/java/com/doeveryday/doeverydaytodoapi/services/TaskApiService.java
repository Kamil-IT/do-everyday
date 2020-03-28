package com.doeveryday.doeverydaytodoapi.services;

import com.doeveryday.doeverydaytodoapi.api.v1.model.TaskDTO;
import javassist.NotFoundException;

import java.util.List;

public interface TaskApiService {

    List<TaskDTO> getAllTasks();

    TaskDTO getTaskById(Long id) throws NotFoundException;

    TaskDTO createTask(TaskDTO taskDTO) throws NotFoundException;

    void deleteTaskById(Long id) throws NotFoundException;

    TaskDTO putTask(TaskDTO taskDTO, Long id) throws NotFoundException;

    List<TaskDTO> getTaskByBoardId(Long boardId);
}
