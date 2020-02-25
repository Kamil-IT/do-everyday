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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@AllArgsConstructor
@Controller
public class TaskController {

    private final BoardService boardService;
    private final TaskService taskService;

    @GetMapping("todo/board/{idBoard}/task/add")
    public String initAddTask(Model model, @PathVariable Long idBoard){
        model.addAttribute("task", new Task());
        model.addAttribute("boardId", idBoard);

        return "todo/board/addtask";
    }

    @PostMapping("todo/board/{idBoard}/task")
    public String addTask(@PathVariable("idBoard") Long idBoard, Task task){
        task.setBoard(boardService.findById(idBoard));
        taskService.saveTask(task);

        return "redirect:/todo/board/index";
    }


}
