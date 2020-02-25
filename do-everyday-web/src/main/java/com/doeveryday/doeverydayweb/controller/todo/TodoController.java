package com.doeveryday.doeverydayweb.controller.todo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TodoController {

    @GetMapping("todo")
    public String mainPage(){
        return "todo/index";
    }
}
