package com.doeveryday.doeverydaytodoapi.services;

import com.doeveryday.doeverydaytodoapi.api.v1.model.TaskManagerDTO;
import javassist.NotFoundException;

import java.util.List;

public interface TaskManagerApiService {

    List<TaskManagerDTO> getAllTaskManager();

    TaskManagerDTO getTaskManagerById(Long id) throws NotFoundException;

    TaskManagerDTO createTaskManager(TaskManagerDTO taskManagerDTO);

    TaskManagerDTO putTaskManager(TaskManagerDTO taskManagerDTO, Long id) throws NotFoundException;

    void deleteTaskManager(Long id);
}
