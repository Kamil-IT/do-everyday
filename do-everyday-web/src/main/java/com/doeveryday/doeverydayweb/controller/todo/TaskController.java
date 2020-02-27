package com.doeveryday.doeverydayweb.controller.todo;

import com.doeveryday.doeverydaytodo.exceptions.NotFoundException;
import com.doeveryday.doeverydaytodo.models.Board;
import com.doeveryday.doeverydaytodo.models.Task;
import com.doeveryday.doeverydaytodo.models.TaskManager;
import com.doeveryday.doeverydaytodo.service.BoardService;
import com.doeveryday.doeverydaytodo.service.TaskManagerService;
import com.doeveryday.doeverydaytodo.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Slf4j
@AllArgsConstructor
@Controller
public class TaskController {

    private final BoardService boardService;
    private final TaskService taskService;
    private final TaskManagerService taskManagerService;

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

        taskManagerService.saveTaskManager(TaskManager.builder().task(task).build());

        return "redirect:/todo/board";
    }

    @GetMapping("todo/board/{idBoard}/task/{idTask}/edit")
    public String initEditTask(Model model, @PathVariable("idBoard") Long idBoard, @PathVariable("idTask") Long idTask){
        Task task = taskService.findById(idTask);
        if (task.getBoard().getId() != idBoard){
            throw new NotFoundException("Not found task id = " + idTask + " with board id = " + idBoard);
        }
        model.addAttribute("task", task);

        return "todo/board/edittask";
    }

    @PutMapping("todo/board/{idBoard}/task")
    public String editTask(Task task, @PathVariable("idboard") Long idBoard){
        task.setBoard(boardService.findById(idBoard));

        taskService.updateTask(task);

        return "redirect:/todo/board";
    }

    @PostMapping("todo/board/task/{id}/delete")
    public String deleteTask(@PathVariable("id") Long id){
        taskService.deleteById(id);
        return "redirect:/todo/board";
    }


}
