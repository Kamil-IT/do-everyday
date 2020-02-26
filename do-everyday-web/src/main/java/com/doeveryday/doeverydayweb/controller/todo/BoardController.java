package com.doeveryday.doeverydayweb.controller.todo;

import com.doeveryday.doeverydaytodo.models.Board;
import com.doeveryday.doeverydaytodo.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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

    @PostMapping("todo/board")
    public String addBoard(Board board){
        if (board.getId() != null){
            boardService.updateBoard(board);
        }
        boardService.saveBord(board);

        return "redirect:/todo/board";
    }

    @GetMapping("todo/board/{id}/edit")
    public String initEditBoard(@PathVariable("id") Long id, Model model){
        model.addAttribute("board", boardService.findById(id));

        return "todo/board/editboard";
    }

    @PutMapping("todo/board")
    public String editBoard(Board board){
        boardService.updateBoard(board);

        return "redirect:/todo/board";
    }
}
