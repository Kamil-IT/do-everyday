package com.doeveryday.doeverydayweb.controller.todo;

import com.doeveryday.doeverydaysecurity.service.AppUserService;
import com.doeveryday.doeverydaytodo.models.Board;
import com.doeveryday.doeverydaytodo.service.BoardService;
import com.doeveryday.doeverydayweb.model.BootstrapAlert;
import com.doeveryday.doeverydayweb.model.MessageToController;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

/**
 * Structure for method = post
 * .../edit  - updating object
 * .../delete - deleting object
 * .../add - adding object
 */

@Slf4j
@AllArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;
    private final AppUserService appUserService;

    @GetMapping("todo/board")
    public String showBoards(Model model, Principal principal) throws NotFoundException {
        if (principal == null) {
            model.addAttribute("boards", boardService.getBoards());
            model.addAttribute("message", MessageToController.builder()
                    .message("If you log in  you will can menage you tasks and access to it")
                    .alert(BootstrapAlert.WARRING)
                    .build());
        } else {
            model.addAttribute("boards", boardService.getBoards(
                    appUserService.findByUsername(principal.getName()).getId()));
        }
        return "todo/board/index";
    }

    @GetMapping("todo/board/archived")
    public String showArchivedBoards(Model model, Principal principal) throws NotFoundException {
        if (principal == null) {
            model.addAttribute("boards", boardService.getBoards());
            model.addAttribute("message", MessageToController.builder()
                    .message("If you log in  you will can menage you tasks and access to it")
                    .alert(BootstrapAlert.WARRING)
                    .build());
        } else {
            model.addAttribute("boards", boardService.getBoards(
                    appUserService.findByUsername(
                            principal.getName()).getId()));
        }
        return "todo/board/archivedboard";
    }

    @GetMapping("todo/board/add")
    public String initAddBoard(Model model) {
        model.addAttribute("board", new Board());

        return "todo/board/boardform";
    }

    @GetMapping("todo/board/{id}/edit")
    public String initEditBoard(@PathVariable("id") Long id, Model model, Principal principal) {
        Board board;
        if (principal != null) {
            try {
                board = boardService.getBoardByIdAndUsername(id, principal.getName());
            } catch (NotFoundException e) {
                return "redirect:error/forbidden";
            }
        } else {
            board = boardService.findById(id);
            if (board.getAppUsers() != null) {
                return "redirect:error/forbidden";
            }
        }

        model.addAttribute("board", board);

        return "todo/board/boardform";
    }

    @PostMapping("todo/board")
    public String addOrUpdateBoard(Board board, Principal principal) throws NotFoundException {
        if (board.getId() != null) {
            boardService.updateBoard(board);
        }
        if (principal == null) {
            boardService.saveBord(board);
        } else {
            boardService.saveBord(board, appUserService.findByUsername(principal.getName()).getId());
        }

        return "redirect:/todo/board";
    }

    @PostMapping("todo/board/{id}/delete")
    public String deleteBoard(@PathVariable("id") Long id, Principal principal) {

        if (principal != null) {
            try {
                boardService.getBoardByIdAndUsername(id, principal.getName());
                boardService.deleteById(id);
            } catch (NotFoundException e) {
                return "redirect:error/forbidden";
            }
        } else if (boardService.findById(id).getAppUsers() != null) {
            return "redirect:error/forbidden";
        }

        boardService.deleteById(id);
        return "redirect:/todo/board";
    }
}
