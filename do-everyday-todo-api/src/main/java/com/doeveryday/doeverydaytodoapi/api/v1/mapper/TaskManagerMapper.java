package com.doeveryday.doeverydaytodoapi.api.v1.mapper;

import com.doeveryday.doeverydaytodo.models.TaskManager;
import com.doeveryday.doeverydaytodoapi.api.v1.model.TaskManagerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = PriorityMapper.class)
public interface TaskManagerMapper {

    @Mapping(source = "task.id", target ="taskId")
    @Mapping(source = "priority", target = "priority", qualifiedByName = "priorityToPriorityDTO")
    TaskManagerDTO taskManagerToTaskMangerDTO(TaskManager taskManager);

    /**
     * After this converting you
     * LOST ID in task to BOARD,
     * LOST ID in task to TASK_MANAGER,
     * LOST List of TASK_MEMBER
     * LOST Priority
     * @param taskManagerDTO
     * @return taskManager
     */
    TaskManager taskManagerDTOToTaskManager(TaskManagerDTO taskManagerDTO);
}
