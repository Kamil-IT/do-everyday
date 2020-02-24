package com.doeveryday.doeverydayweb.controller.todo;

import com.doeveryday.doeverydaytodo.models.Board;
import com.doeveryday.doeverydaytodo.models.Task;
import com.doeveryday.doeverydaytodo.service.BoardService;
import com.doeveryday.doeverydaytodo.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@AllArgsConstructor
@Controller
public class TaskController {

    private final BoardService boardService;
    private final TaskService taskService;

    @GetMapping("todo/board/task/add")
    public String initAddTask(Model model){
        model.addAttribute("task", new Task());
        model.addAttribute("boards", boardService.getBoards());

        return "todo/board/addtask";
    }

    @PostMapping("todo/board/task")
    public String addTask(Task task, Board board){
        task.setBoard(board);
        taskService.saveTask(task);

        return "index";
    }


}
