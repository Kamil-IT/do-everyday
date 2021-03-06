package com.doeveryday.doeverydaytodoapi.services;

import com.doeveryday.doeverydaytodo.models.Task;
import com.doeveryday.doeverydaytodo.repository.BoardRepository;
import com.doeveryday.doeverydaytodo.repository.TaskManagerRepository;
import com.doeveryday.doeverydaytodo.repository.TaskRepository;
import com.doeveryday.doeverydaytodoapi.api.v1.mapper.TaskMapper;
import com.doeveryday.doeverydaytodoapi.api.v1.model.TaskDTO;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskApiServiceImpl implements TaskApiService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final BoardRepository boardRepository;
    private final TaskManagerRepository taskManagerRepository;

    public TaskApiServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper, BoardRepository boardRepository, TaskManagerRepository taskManagerRepository) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.boardRepository = boardRepository;
        this.taskManagerRepository = taskManagerRepository;
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        List<TaskDTO> taskDTOs = new ArrayList<>();
        taskRepository.findAll().forEach(task -> taskDTOs.add(taskMapper.taskToTaskDTO(task)));
        return taskDTOs;
    }

    @Override
    public TaskDTO getTaskById(Long id) throws NotFoundException {
        return taskMapper.taskToTaskDTO(taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found board with id: " + id)));
    }

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) throws NotFoundException {
        taskDTO.setId(null);
        Task taskToSave = taskMapper.taskDTOToTask(taskDTO);
        addBoardAndTask(taskDTO, taskToSave);
        Task taskSaved = taskRepository.save(taskToSave);
        return taskMapper.taskToTaskDTO(taskSaved);
    }

    @Override
    public void deleteTaskById(Long id) throws NotFoundException {
        if (taskRepository.existsById(id)){
            taskRepository.deleteById(id);
        }
        else {
            throw new NotFoundException("Not found task with id: " + id);
        }
    }

    @Override
    public TaskDTO putTask(TaskDTO taskDTO, Long id) throws NotFoundException {
        if (id == null){
            throw new NullPointerException("Id cannot be null");
        }
        else if (!taskRepository.existsById(id)){
            throw new NotFoundException("Not found task with id: " + id);
        }
        Task taskToSave = taskMapper.taskDTOToTask(taskDTO);
        taskToSave.setId(id);
        addBoardAndTask(taskDTO, taskToSave);
        return taskMapper.taskToTaskDTO(taskRepository.save(taskToSave));
    }

    @Override
    public List<TaskDTO> getTaskByBoardId(Long boardId) {
        return taskMapper.tasksToTasksDTO(taskRepository.findAllByBoardId(boardId));
    }

    private Task addBoardAndTask(TaskDTO taskDTO, Task taskToSave) throws NotFoundException {
        if (taskDTO.getBoardId() != null){
            taskToSave.setBoard(
                    boardRepository.findById(taskDTO.getBoardId())
                            .orElseThrow(() -> new NotFoundException("Not found board with id " + taskDTO.getId())));
        }
        if (taskDTO.getTaskManagerId() != null){
            taskToSave.setTaskManager(
                    taskManagerRepository.findById(taskDTO.getTaskManagerId())
                            .orElseThrow(() -> new NotFoundException("Not found board with id " + taskDTO.getId())));
        }
        return taskToSave;
    }
}
