package com.doeveryday.doeverydaytodoapi.api.v1.mapper;

import com.doeveryday.doeverydaytodo.models.BaseEntity;
import com.doeveryday.doeverydaytodo.models.TaskManager;
import com.doeveryday.doeverydaytodo.models.TaskMember;
import com.doeveryday.doeverydaytodoapi.api.v1.model.TaskMemberDTO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

import static com.doeveryday.doeverydaytodoapi.api.v1.mapper.ConstValues.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskMemberMapperTest {

    TaskMemberMapper taskMemberMapper = Mappers.getMapper(TaskMemberMapper.class);

    @Test
    void taskMemberToTaskMemberDTO() {
        TaskMember taskMember = new TaskMember();

        TaskManager taskManager = new TaskManager();
        taskManager.setId(TASK_MANAGER_ID);

        taskMember.setId(TASK_MEMBER_ID);
        taskMember.setAppUsers(APP_USER);
        taskMember.setTaskManagers(List.of(taskManager));

        TaskMemberDTO taskMemberDTO = taskMemberMapper.taskMemberToTaskMemberDTO(taskMember);

        assertEquals(taskMember.getId(), taskMemberDTO.getId());
        assertEquals(taskMember.getAppUsers().getId(), taskMemberDTO.getAppUserId());
        List<Long> listOfTaskManagersId = taskMember.getTaskManagers().stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toList());
        assertEquals(listOfTaskManagersId, taskMemberDTO.getTaskManagerIds());

    }

    @Test
    void taskMemberDTOToTaskMember() {
        TaskMemberDTO taskMemberDTO = new TaskMemberDTO();

        TaskManager taskManager = new TaskManager();
        taskManager.setId(TASK_MANAGER_ID);

        taskMemberDTO.setId(TASK_MEMBER_ID);
        taskMemberDTO.setAppUserId(APP_USER.getId());
        taskMemberDTO.setTaskManagerIds(List.of(TASK_MANAGER_ID));

        TaskMember taskMember = taskMemberMapper.taskMemberDTOToTaskMember(taskMemberDTO);


        assertEquals(taskMemberDTO.getId(), taskMember.getId());
    }
}