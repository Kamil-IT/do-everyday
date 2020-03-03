package com.doeveryday.doeverydayweb.controller.todo;

import com.doeveryday.doeverydaytodo.exceptions.NotFoundException;
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

@Slf4j
@AllArgsConstructor
@Controller
public class TaskController {

    private final BoardService boardService;
    private final TaskService taskService;
    private final TaskManagerService taskManagerService;

    @GetMapping("todo/board/{idBoard}/task/add")
    public String initAddTask(Model model, @PathVariable Long idBoard){
        if (!boardService.existsById(idBoard)){
            throw new NotFoundException("Not found board with id: " + idBoard);
        }
        model.addAttribute("task", new Task());
        model.addAttribute("boardId", idBoard);

        return "todo/board/addtask";
    }

    @GetMapping("todo/board/task/{idTask}/edit")
    public String initEditTask(Model model, @PathVariable("idTask") Long idTask){
        model.addAttribute("task", taskService.findById(idTask));

        return "todo/board/edittask";
    }

    @PostMapping("todo/board/{idBoard}/task")
    public String addAndUpdateTask(@PathVariable("idBoard") Long idBoard, Task task){
        task.setBoard(boardService.findById(idBoard));
        if (task.getId() == null){
            taskService.saveTask(task);
        }
        else {
            taskService.updateTask(task);
        }

        return "redirect:/todo/board";
    }

    @PostMapping("todo/board/task/{id}/delete")
    public String deleteTask(@PathVariable("id") Long id){
        taskService.deleteById(id);
        return "redirect:/todo/board";
    }

    @PostMapping("todo/board/task/{id}/donechange")
    public String doneTask(@PathVariable("id") Long id){
        TaskManager taskManager = taskManagerService.findByTaskId(id);
        taskManager.setDone(!taskManager.isDone());
        taskManagerService.updateTaskManager(taskManager);
        return "redirect:/todo/board";
    }


}
