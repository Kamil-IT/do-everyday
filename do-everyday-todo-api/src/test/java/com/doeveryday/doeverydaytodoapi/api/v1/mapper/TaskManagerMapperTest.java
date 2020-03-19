package com.doeveryday.doeverydaytodoapi.api.v1.mapper;

import com.doeveryday.doeverydaytodo.models.Board;
import com.doeveryday.doeverydaytodo.models.Task;
import com.doeveryday.doeverydaytodo.models.TaskManager;
import com.doeveryday.doeverydaytodoapi.api.v1.model.TaskDTO;
import com.doeveryday.doeverydaytodoapi.api.v1.model.TaskManagerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {TaskManagerMapperImpl.class, PriorityMapperImpl.class})
class TaskManagerMapperTest {

    @Autowired
    private TaskManagerMapper taskManagerMapper;

    @Test
    void taskManagerToTaskMangerDTO() {
        Task task = new Task();
        TaskManager taskManager = new TaskManager();
        setDataEntity(task, taskManager);

        TaskManagerDTO taskManagerDTO = taskManagerMapper.taskManagerToTaskMangerDTO(taskManager);

        assertEquals(taskManager.getId(), taskManagerDTO.getId());
        assertEquals(taskManager.getPriority().name(), taskManagerDTO.getPriority());
        assertEquals(taskManager.isDone(), taskManagerDTO.isDone());
    }


    @Test
    void taskManagerDTOToTaskManager() {
        TaskDTO taskDTO = new TaskDTO();
        TaskManagerDTO taskManagerDTO = new TaskManagerDTO();
        setDataDTO(taskDTO, taskManagerDTO);

        TaskManager taskManager = taskManagerMapper.taskManagerDTOToTaskManager(taskManagerDTO);

        assertEquals(taskManagerDTO.getId(), taskManager.getId());
        assertEquals(taskManagerDTO.isDone(), taskManager.isDone());
    }

    private void setDataEntity(Task task, TaskManager taskManager) {
        //        Set task
        task.setId(ConstValues.TASK_ID);
        task.setCreateDate(ConstValues.TASK_CREATE_DATE);
        task.setDescription(ConstValues.DESCRIPTION_TASK);
        task.setName(ConstValues.TASK_NAME);
//        set task manager
        taskManager.setId(ConstValues.TASK_MANAGER_ID);
        taskManager.setDone(true);
        taskManager.setTask(task);
        taskManager.setPriority(ConstValues.IMPORTANT);
        task.setTaskManager(taskManager);
        task.setBoard(new Board());
    }

    private void setDataDTO(TaskDTO taskDTO, TaskManagerDTO taskManagerDTO) {
        //        Set taskDTO
        taskDTO.setId(ConstValues.TASK_ID);
        taskDTO.setCreateDate(ConstValues.TASK_CREATE_DATE);
        taskDTO.setDescription(ConstValues.DESCRIPTION_TASK);
        taskDTO.setName(ConstValues.TASK_NAME);
//        set task manager
        taskManagerDTO.setId(ConstValues.TASK_MANAGER_ID);
        taskManagerDTO.setDone(true);
        taskManagerDTO.setId(taskDTO.getId());
        taskManagerDTO.setPriority(ConstValues.IMPORTANT.name());
        taskDTO.setTaskManagerId(taskManagerDTO.getId());
    }

}