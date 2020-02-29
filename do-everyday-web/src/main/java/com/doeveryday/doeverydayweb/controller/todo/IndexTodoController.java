package com.doeveryday.doeverydayweb.controller.todo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Slf4j
@Controller
public class IndexTodoController {

    @GetMapping(value = {"todo", "todo/index", "todo/board/index"})
    public String showBoards(){
        return "redirect:todo/board";
    }
}
