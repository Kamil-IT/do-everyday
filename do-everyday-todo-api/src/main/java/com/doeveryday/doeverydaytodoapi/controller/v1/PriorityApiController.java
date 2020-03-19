package com.doeveryday.doeverydaytodoapi.controller.v1;

import com.doeveryday.doeverydaytodoapi.api.v1.model.PriorityDTO;
import com.doeveryday.doeverydaytodoapi.services.PriorityApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PriorityApiController.BASE_URL)
public class PriorityApiController {

    public static final String BASE_URL = "api/v1/priority";

    private final PriorityApiService priorityApiService;

    public PriorityApiController(PriorityApiService priorityApiService) {
        this.priorityApiService = priorityApiService;
    }

    @GetMapping
    public PriorityDTO[] getAllPriorities(){
        return priorityApiService.getAllPriority();
    }

    @GetMapping("/{name}")
    public PriorityDTO getPrioritiesByName(@PathVariable("name") String name){
        return priorityApiService.getPriorityByName(name.toUpperCase());
    }
}
