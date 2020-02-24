package com.doeveryday.doeverydayweb.controller.todo;

import com.doeveryday.doeverydaytodo.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@AllArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;

    @GetMapping("todo/board/index")
    public String showBoards(Model model){
        model.addAttribute("boards", boardService.getBoards());
        return "todo/board/index";
    }
}
