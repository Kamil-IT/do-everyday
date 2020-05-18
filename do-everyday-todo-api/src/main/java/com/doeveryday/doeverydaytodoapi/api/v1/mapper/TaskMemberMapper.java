package com.doeveryday.doeverydaytodoapi.api.v1.mapper;

import com.doeveryday.doeverydaytodo.models.BaseEntity;
import com.doeveryday.doeverydaytodo.models.TaskManager;
import com.doeveryday.doeverydaytodo.models.TaskMember;
import com.doeveryday.doeverydaytodoapi.api.v1.model.TaskMemberDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper()
public interface TaskMemberMapper {

    @Mapping(target = "appUserId", source = "appUsers.id")
    @Mapping(target = "taskManagerIds", source = "taskManagers", qualifiedByName = "taskManagersToTaskManagerIds")
    TaskMemberDTO taskMemberToTaskMemberDTO(TaskMember taskMember);

    /**
     * After this converting you
     * LOST taskManagers,
     * LOST appUser
     */
    TaskMember taskMemberDTOToTaskMember(TaskMemberDTO taskMemberDTO);

    default List<Long> taskManagersToTaskManagerIds(List<TaskManager> taskManagers){
        return taskManagers.stream().map(BaseEntity::getId).collect(Collectors.toList());
    }

}
