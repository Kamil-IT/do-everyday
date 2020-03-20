package com.doeveryday.doeverydaytodoapi.api.v1.mapper;

import com.doeveryday.doeverydaytodo.models.TaskManager;
import com.doeveryday.doeverydaytodo.models.TaskMember;
import com.doeveryday.doeverydaytodoapi.api.v1.model.TaskManagerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = PriorityMapper.class)
public interface TaskManagerMapper {

    @Mapping(source = "task.id", target ="taskId")
    @Mapping(source = "priority", target = "priority", qualifiedByName = "priorityToPriorityDTO")
    @Mapping(source = "taskMember", target = "taskMembers", qualifiedByName = "taskMembersToListTaskMemberId")
    TaskManagerDTO taskManagerToTaskMangerDTO(TaskManager taskManager);

    /**
     * After this converting you
     * LOST List of TASK_MEMBER
     * LOST Priority
     */
    TaskManager taskManagerDTOToTaskManager(TaskManagerDTO taskManagerDTO);
    
    default List<Long> taskMembersToListTaskMemberId(List<TaskMember> taskMembers){
        List<Long> ids = new ArrayList<>();
        for (TaskMember taskMember :
                taskMembers) {
            ids.add(taskMember.getId());
        }
        return ids;
    }
}
