package com.doeveryday.doeverydayweb.controller.todo;

import com.doeveryday.doeverydaytodo.models.Board;
import com.doeveryday.doeverydaytodo.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@AllArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;

    @GetMapping("todo/board")
    public String showBoards(Model model){
        model.addAttribute("boards", boardService.getBoards());
        return "todo/board/index";
    }

    @GetMapping("todo/board/add")
    public String initAddBoard(Model model){
        model.addAttribute("board", new Board());

        return "todo/board/addboard";
    }

    @PostMapping("todo/board/add")
    public String addBoard(Board board){
        boardService.saveBord(board);

        return "redirect:/todo/board";
    }
}
