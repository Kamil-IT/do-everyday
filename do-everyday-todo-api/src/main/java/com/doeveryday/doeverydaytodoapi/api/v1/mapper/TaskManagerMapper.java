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

    TaskManager taskManagerDTOToTaskManager(TaskManagerDTO taskManagerDTO);
}
