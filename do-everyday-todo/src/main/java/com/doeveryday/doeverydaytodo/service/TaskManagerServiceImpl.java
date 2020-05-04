package com.doeveryday.doeverydaytodo.service;

import com.doeveryday.doeverydaytodo.exceptions.NotFoundException;
import com.doeveryday.doeverydaytodo.models.TaskManager;
import com.doeveryday.doeverydaytodo.repository.TaskManagerRepository;
import com.doeveryday.doeverydaytodo.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class TaskManagerServiceImpl implements TaskManagerService{

    private final TaskManagerRepository taskManagerRepository;
    private final TaskRepository taskRepository;


    @Override
    public TaskManager saveTaskManager(TaskManager taskManager) {
        return taskManagerRepository.save(taskManager);
    }

    @Override
    public List<TaskManager> getTaskManager() {
        List<TaskManager> taskManagers = new ArrayList<>();
        taskManagerRepository.findAll().forEach(taskManagers::add);
        return taskManagers;
    }

    @Override
    public TaskManager findById(Long id) {
        return taskManagerRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Not found TaskManager with id: = " + id));
    }

    @Override
    public void deleteById(Long id) {
        if (taskManagerRepository.existsById(id)){
            taskManagerRepository.deleteById(id);
        }
        else {
            throw new NotFoundException("Not found TaskManager with id: = " + id);
        }

    }

    @Override
    public void updateTaskManager(TaskManager taskManager) {
        if (taskManager.getId() == null){
            throw new NullPointerException("Task id cannot be null");
        }
        else if (!taskManagerRepository.existsById(taskManager.getId())){
            throw new NotFoundException("Not found TaskManager with id: = " + taskManager.getId());
        }
        else {
            taskManagerRepository.save(taskManager);
        }
    }
    /**
     * @param taskId Task id, task which this id must have assigned task manager
     * @return Task manager with have at least task with id which was uploads in param
     */
    @Override
    public TaskManager findByTaskId(Long taskId) {
        return taskManagerRepository.findFirstByTask_Id(taskId)
                .orElseThrow(() ->
                        new NotFoundException("Not found task manager which have task with id: =" + taskId));
    }
}
