package com.doeveryday.doeverydaytodoapi.controller.v1;

import com.doeveryday.doeverydaytodoapi.api.v1.model.TaskDTO;
import com.doeveryday.doeverydaytodoapi.api.v1.model.TaskListDTO;
import com.doeveryday.doeverydaytodoapi.services.BoardApiService;
import com.doeveryday.doeverydaytodoapi.services.TaskApiService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(TaskApiController.BASE_URL)
public class TaskApiController {
    public static final String BASE_URL = "/api/v1/";

    private final BoardApiService boardApiService;
    private final TaskApiService taskApiService;

    public TaskApiController(BoardApiService boardApiService, TaskApiService taskApiService) {
        this.boardApiService = boardApiService;
        this.taskApiService = taskApiService;
    }

    @GetMapping("task")
    @ResponseStatus(HttpStatus.OK)
    public TaskListDTO getAllTasks(){
        return new TaskListDTO(taskApiService.getAllTasks());
    }

    @GetMapping("board/{boardid}/task")
    @ResponseStatus(HttpStatus.OK)
    public TaskListDTO getAllTaskByBoard(@PathVariable("boardid") Long boardId){
        return new TaskListDTO(taskApiService.getTaskByBoardId(boardId));
    }

    @GetMapping("task/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDTO getTaskById(@PathVariable("id") Long id){
        return taskApiService.getTaskById(id);
    }

    @PostMapping("task")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO createTask(@RequestBody TaskDTO taskDTO){
        return taskApiService.createTask(taskDTO);
    }

    @PutMapping("task/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDTO putTaskById(@RequestBody TaskDTO taskDTO, @PathVariable("id") Long id){
        return taskApiService.putTask(taskDTO, id);
    }

    @PutMapping("task")
    @ResponseStatus(HttpStatus.OK)
    public TaskDTO putTask(@RequestBody TaskDTO taskDTO){
        return taskApiService.putTask(taskDTO, taskDTO.getId());
    }

    @DeleteMapping("task/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteTask(@PathVariable("id") Long id){
        taskApiService.deleteTaskById(id);
        return "Delete task with id = " + id + " was successful";
    }
}
