package com.doeveryday.doeverydaytodoapi.api.v1.mapper;

import com.doeveryday.doeverydaytodo.models.Task;
import com.doeveryday.doeverydaytodoapi.api.v1.model.TaskDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    /**
     * After this converting you
     * LOST ID to BOARD,
     * LOST ID to TASK_MANAGER
     * @param taskDTO
     * @return
     */
    Task taskDTOToTask(TaskDTO taskDTO);

    @Mapping(source = "board.id", target = "boardId")
    @Mapping(source = "taskManager.id", target = "taskManagerId")
    TaskDTO taskToTaskDTO(Task task);

    @Named("tasksToTasksDTO")
    default List<TaskDTO> tasksToTasksDTO(List<Task> tasks){
        List<TaskDTO> taskDTOs = new ArrayList<>();
        for (Task task: tasks){
            taskDTOs.add(taskToTaskDTO(task));
        }
        return taskDTOs;
    }

}
