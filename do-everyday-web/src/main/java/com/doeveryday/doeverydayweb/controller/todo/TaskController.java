package com.doeveryday.doeverydayweb.controller.todo;

import com.doeveryday.doeverydaytodo.models.Task;
import com.doeveryday.doeverydaytodo.models.TaskManager;
import com.doeveryday.doeverydaytodo.service.BoardService;
import com.doeveryday.doeverydaytodo.service.TaskManagerService;
import com.doeveryday.doeverydaytodo.service.TaskService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Slf4j
@AllArgsConstructor
@Controller
public class TaskController {

    private final BoardService boardService;
    private final TaskService taskService;
    private final TaskManagerService taskManagerService;

    @GetMapping("todo/board/{idBoard}/task/add")
    public String initAddTask(Model model, @PathVariable Long idBoard, Principal principal) throws NotFoundException {
        if (principal != null) {
            try {
                boardService.getBoardByIdAndUsername(idBoard, principal.getName());
            } catch (javassist.NotFoundException e) {
                return "redirect:error/forbidden";
            }
        } else if (boardService.findById(idBoard).getAppUsers() != null) {
            return "redirect:error/forbidden";
        }
        Task task = new Task();
        task.setTaskManager(new TaskManager());
        model.addAttribute("task", task);
        model.addAttribute("boardId", idBoard);

        return "todo/board/addtask";
    }

    @GetMapping("todo/board/task/{idTask}/edit")
    public String initEditTask(Model model, @PathVariable("idTask") Long idTask, Principal principal) {
        Task task = taskService.findById(idTask);
        Long id = task.getBoard().getId();
        if (task.getBoard() != null && principal != null) {
            try {
                boardService.getBoardByIdAndUsername(id, principal.getName());
            } catch (javassist.NotFoundException e) {
                return "redirect:error/forbidden";
            }
        } else if (boardService.findById(id).getAppUsers() != null) {
            return "redirect:error/forbidden";
        }

        model.addAttribute("task", task);

        return "todo/board/edittask";
    }

    @PostMapping("todo/board/{idBoard}/task")
    public String addOrUpdateTask(@PathVariable("idBoard") Long idBoard, Task task, Principal principal) {
        if (principal != null) {
            try {
                boardService.getBoardByIdAndUsername(idBoard, principal.getName());
            } catch (javassist.NotFoundException e) {
                return "redirect:error/forbidden";
            }
        } else if (boardService.findById(idBoard).getAppUsers() != null) {
            return "redirect:error/forbidden";
        }
        task.setBoard(boardService.findById(idBoard));
        if (task.getId() == null) {
            taskService.saveTask(task);
        } else {
            task.getTaskManager().setDone(taskService.findById(task.getId()).getTaskManager().isDone());
            Long taskManagerId = taskService.findById(task.getId()).getTaskManager().getId();
            task.getTaskManager().setId(taskManagerId);
            taskService.updateTask(task);
        }

        return "redirect:/todo/board";
    }

    @PostMapping("todo/board/task/{id}/delete")
    public String deleteTask(@PathVariable("id") Long idTask, Principal principal) {
        Task task = taskService.findById(idTask);
        Long id = task.getBoard().getId();
        if (task.getBoard() != null && principal != null) {
            try {
                boardService.getBoardByIdAndUsername(id, principal.getName());
            } catch (javassist.NotFoundException e) {
                return "redirect:error/forbidden";
            }
        } else if (boardService.findById(id).getAppUsers() != null) {
            return "redirect:error/forbidden";
        }
        taskService.deleteById(id);
        return "redirect:/todo/board";
    }

    @PostMapping("todo/board/task/{id}/donechange")
    public String doneTask(@PathVariable("id") Long idTask, Principal principal) {
        Task task = taskService.findById(idTask);
        Long id = task.getBoard().getId();
        if (task.getBoard() != null && principal != null) {
            try {
                boardService.getBoardByIdAndUsername(id, principal.getName());
            } catch (javassist.NotFoundException e) {
                return "redirect:error/forbidden";
            }
        } else if (boardService.findById(id).getAppUsers() != null) {
            return "redirect:error/forbidden";
        }
        TaskManager taskManager = task.getTaskManager();
        taskManager.setDone(!taskManager.isDone());
        taskManagerService.updateTaskManager(taskManager);
        return "redirect:/todo/board";
    }


}
