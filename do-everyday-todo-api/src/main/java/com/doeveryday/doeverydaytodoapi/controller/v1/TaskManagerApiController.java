package com.doeveryday.doeverydaytodoapi.controller.v1;

import com.doeveryday.doeverydaytodoapi.api.v1.model.TaskManagerDTO;
import com.doeveryday.doeverydaytodoapi.api.v1.model.TaskManagerListDTO;
import com.doeveryday.doeverydaytodoapi.services.TaskManagerApiService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(TaskManagerApiController.BASE_URL)
public class TaskManagerApiController {
    public static final String BASE_URL = "/api/v1/taskmanager";

    private final TaskManagerApiService taskManagerApiService;

    public TaskManagerApiController(TaskManagerApiService taskManagerApiService) {
        this.taskManagerApiService = taskManagerApiService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public TaskManagerListDTO getAllTaskManager(){
        return new TaskManagerListDTO(taskManagerApiService.getAllTaskManager());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskManagerDTO getTaskManagerById(@PathVariable("id") Long id){
        return taskManagerApiService.getTaskManagerById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskManagerDTO createTaskManager(TaskManagerDTO taskManagerDTO){
        return taskManagerApiService.createTaskManager(taskManagerDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskManagerDTO putTaskManager(@PathVariable("id") Long id, @RequestBody TaskManagerDTO taskManagerDTO){
        return taskManagerApiService.putTaskManager(taskManagerDTO, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteTaskManager(@PathVariable("id") Long id){
        taskManagerApiService.deleteTaskManager(id);
        return "Deleted task manager with id = " + id + " was successful";
    }


}
