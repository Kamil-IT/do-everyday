package com.doeveryday.doeverydaytodo.service;

import com.doeveryday.doeverydaytodo.exceptions.NotFoundException;
import com.doeveryday.doeverydaytodo.models.Board;
import com.doeveryday.doeverydaytodo.models.Task;
import com.doeveryday.doeverydaytodo.repository.BoardRepository;
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

    @Override
    public Task saveTask(Task task) {
        task.setCreateDate(new Date());
        return taskRepository.save(task);
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
//        taskRepository.save(task);
        Long boardId = task.getBoard().getId();
        if (boardId == null || task.getId() == null){
            throw new NullPointerException("Board and task id cannot by null");
        }
        else {
            Optional<Board> boardOptional = boardRepository.findById(boardId);
            if (boardOptional.isEmpty()){
                throw new NotFoundException("Not found board with id: " + boardId);
            }
            else {
                Board boardToSave = boardOptional.get();
                Optional<Task> taskOptional = boardToSave.getTasks().stream().filter(c -> c.getId().equals(task.getId())).findFirst();
                if (taskOptional.isEmpty()){
                    throw new NotFoundException("Not found task with id: " + task.getId());
                }
                else {
                    boardRepository.save(boardToSave);
                }

            }
        }
    }
}
