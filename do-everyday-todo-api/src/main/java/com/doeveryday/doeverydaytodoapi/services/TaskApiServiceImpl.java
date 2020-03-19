package com.doeveryday.doeverydaytodoapi.services;

import com.doeveryday.doeverydaytodo.exceptions.NotFoundException;
import com.doeveryday.doeverydaytodo.models.Task;
import com.doeveryday.doeverydaytodo.repository.TaskRepository;
import com.doeveryday.doeverydaytodoapi.api.v1.mapper.TaskMapper;
import com.doeveryday.doeverydaytodoapi.api.v1.model.TaskDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskApiServiceImpl implements TaskApiService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskApiServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        List<TaskDTO> taskDTOs = new ArrayList<>();
        taskRepository.findAll().forEach(task -> taskDTOs.add(taskMapper.taskToTaskDTO(task)));
        return taskDTOs;
    }

    @Override
    public TaskDTO getTaskById(Long id) {
        return taskMapper.taskToTaskDTO(taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found board with id: " + id)));
    }

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        taskDTO.setId(null);
        Task taskSaved = taskRepository.save(taskMapper.taskDTOToTask(taskDTO));
        return taskMapper.taskToTaskDTO(taskSaved);
    }

    @Override
    public void deleteTaskById(Long id) {
        if (taskRepository.existsById(id)){
            taskRepository.deleteById(id);
        }
        else {
            throw new NotFoundException("Not found task with id: " + id);
        }
    }

    @Override
    public TaskDTO putTask(TaskDTO taskDTO, Long id) {
        if (id == null){
            throw new NullPointerException("Id cannot be null");
        }
        else if (!taskRepository.existsById(id)){
            throw new NotFoundException("Not found task with id: " + id);
        }

        Task taskToSave = taskMapper.taskDTOToTask(taskDTO);
        taskToSave.setId(id);
        return taskMapper.taskToTaskDTO(taskRepository.save(taskToSave));
    }

    @Override
    public List<TaskDTO> getTaskByBoardId(Long boardId) {
        return taskMapper.tasksToTasksDTO(taskRepository.findAllByBoardId(boardId));
    }
}
