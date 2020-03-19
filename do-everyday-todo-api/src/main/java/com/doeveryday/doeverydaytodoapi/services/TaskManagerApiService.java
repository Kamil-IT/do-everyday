package com.doeveryday.doeverydaytodoapi.services;

import com.doeveryday.doeverydaytodoapi.api.v1.model.TaskManagerDTO;

import java.util.List;

public interface TaskManagerApiService {

    List<TaskManagerDTO> getAllTaskManager();

    TaskManagerDTO getTaskManagerById(Long id);

    TaskManagerDTO createTaskManager(TaskManagerDTO taskManagerDTO);

    TaskManagerDTO putTaskManager(TaskManagerDTO taskManagerDTO, Long id);

    void deleteTaskManager(Long id);
}
