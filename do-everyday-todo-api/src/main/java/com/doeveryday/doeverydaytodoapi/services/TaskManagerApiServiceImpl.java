package com.doeveryday.doeverydaytodoapi.services;

import com.doeveryday.doeverydaytodo.exceptions.NotFoundException;
import com.doeveryday.doeverydaytodo.models.TaskManager;
import com.doeveryday.doeverydaytodo.repository.TaskManagerRepository;
import com.doeveryday.doeverydaytodoapi.api.v1.mapper.TaskManagerMapper;
import com.doeveryday.doeverydaytodoapi.api.v1.model.TaskManagerDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskManagerApiServiceImpl implements TaskManagerApiService {

    private final TaskManagerRepository taskManagerRepository;
    private final TaskManagerMapper taskManagerMapper;

    public TaskManagerApiServiceImpl(TaskManagerRepository taskManagerRepository, TaskManagerMapper taskManagerMapper) {
        this.taskManagerRepository = taskManagerRepository;
        this.taskManagerMapper = taskManagerMapper;
    }

    @Override
    public List<TaskManagerDTO> getAllTaskManager() {
        List<TaskManagerDTO> taskManagerDTOs = new ArrayList<>();
        taskManagerRepository.findAll()
                .forEach(taskManager -> taskManagerDTOs.add(
                        taskManagerMapper.taskManagerToTaskMangerDTO(taskManager)));
        return taskManagerDTOs;
    }

    @Override
    public TaskManagerDTO getTaskManagerById(Long id) {
        return taskManagerMapper.taskManagerToTaskMangerDTO(
                taskManagerRepository
                        .findById(id).orElseThrow(() -> new NotFoundException("Not found board with id: " + id)));
    }

    @Override
    public TaskManagerDTO createTaskManager(TaskManagerDTO taskManagerDTO) {
        taskManagerDTO.setId(null);
        TaskManager taskManagerToSave = taskManagerMapper.taskManagerDTOToTaskManager(taskManagerDTO);
        return taskManagerMapper.taskManagerToTaskMangerDTO(
                taskManagerRepository.save(taskManagerToSave));
    }

    @Override
    public TaskManagerDTO putTaskManager(TaskManagerDTO taskManagerDTO, Long id) {
        if (id == null){
            throw new NullPointerException("Id cannot be null");
        }
        else if (!taskManagerRepository.existsById(id)){
            throw new NotFoundException("Not found task with id: " + id);
        }

        TaskManager taskManagerToSave = taskManagerMapper.taskManagerDTOToTaskManager(taskManagerDTO);
        taskManagerToSave.setId(id);
        return taskManagerMapper.taskManagerToTaskMangerDTO(taskManagerRepository.save(taskManagerToSave));
    }

    @Override
    public void deleteTaskManager(Long id) {
        taskManagerRepository.deleteById(id);
    }
}
