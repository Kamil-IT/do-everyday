package com.doeveryday.doeverydaytodo.service;

import com.doeveryday.doeverydaytodo.exceptions.NotFoundException;
import com.doeveryday.doeverydaytodo.models.Task;
import com.doeveryday.doeverydaytodo.models.TaskManager;
import com.doeveryday.doeverydaytodo.repository.BoardRepository;
import com.doeveryday.doeverydaytodo.repository.TaskManagerRepository;
import com.doeveryday.doeverydaytodo.repository.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    public final TaskRepository taskRepository;
    public final BoardRepository boardRepository;
    public final TaskManagerRepository taskManagerRepository;

    @Override
    public Task saveTask(Task task) {
        task.setCreateDate(new Date());
        Task taskSaved = taskRepository.save(task);
        if (taskSaved.getTaskManager() == null){
            TaskManager taskManager = new TaskManager();
            taskManager.setTask(taskSaved);
            task.setTaskManager(taskManager);
            taskRepository.save(taskSaved);
        }
        else {
            TaskManager taskManager = taskSaved.getTaskManager();
            taskManager.setTask(taskSaved);
            taskManagerRepository.save(taskManager);
        }
        return taskSaved;
    }

    @Override
    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(tasks::add);
        return tasks;
    }

    @Override
    public Task findById(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isEmpty()){
            throw new NotFoundException("Not found task with id: " + id);
        }
        else {
            return taskOptional.get();
        }
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void updateTask(Task task) {
        if (taskRepository.existsById(task.getId())){
            TaskManager taskManager = taskRepository.save(task).getTaskManager();
            taskManager.setTask(task);
            taskManagerRepository.save(taskManager);
        }
        else {
            log.error("Not found board with id: " + task.getId());
            throw new NotFoundException("Not found board with id: " + task.getId());
        }
    }

    @Override
    public boolean existsById(Long id) {
        return taskRepository.existsById(id);
    }
}
