package com.doeveryday.doeverydaytodoapi.controller.v1;

import com.doeveryday.doeverydaytodoapi.api.v1.model.PriorityDTO;
import com.doeveryday.doeverydaytodoapi.services.PriorityApiService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PriorityApiController.BASE_URL)
public class PriorityApiController {

    public static final String BASE_URL = "/api/v1/priority";

    private final PriorityApiService priorityApiService;

    public PriorityApiController(PriorityApiService priorityApiService) {
        this.priorityApiService = priorityApiService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PriorityDTO[] getAllPriorities(){
        return priorityApiService.getAllPriority();
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public PriorityDTO getPrioritiesByName(@PathVariable("name") String name){
        return priorityApiService.getPriorityByName(name.toUpperCase());
    }
}
