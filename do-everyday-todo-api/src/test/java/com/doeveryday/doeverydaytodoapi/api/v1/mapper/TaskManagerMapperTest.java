package com.doeveryday.doeverydaytodoapi.api.v1.mapper;

import com.doeveryday.doeverydaytodo.models.Board;
import com.doeveryday.doeverydaytodo.models.Priority;
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

    public static final Priority IMPORTANT = Priority.IMPORTANT;
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
        task.setId(BoardMapperTest.TASK_ID);
        task.setCreateDate(BoardMapperTest.TASK_CREATE_DATE);
        task.setDescription(BoardMapperTest.DESCRIPTION_TASK);
        task.setName(BoardMapperTest.TASK_NAME);
//        set task manager
        taskManager.setId(BoardMapperTest.TASK_MANAGER_ID);
        taskManager.setDone(true);
        taskManager.setTask(task);
        taskManager.setPriority(IMPORTANT);
        task.setTaskManager(taskManager);
        task.setBoard(new Board());
    }

    private void setDataDTO(TaskDTO taskDTO, TaskManagerDTO taskManagerDTO) {
        //        Set taskDTO
        taskDTO.setId(BoardMapperTest.TASK_ID);
        taskDTO.setCreateDate(BoardMapperTest.TASK_CREATE_DATE);
        taskDTO.setDescription(BoardMapperTest.DESCRIPTION_TASK);
        taskDTO.setName(BoardMapperTest.TASK_NAME);
//        set task manager
        taskManagerDTO.setId(BoardMapperTest.TASK_MANAGER_ID);
        taskManagerDTO.setDone(true);
        taskManagerDTO.setId(taskDTO.getId());
        taskManagerDTO.setPriority(IMPORTANT.name());
        taskDTO.setTaskManagerId(taskManagerDTO.getId());
    }

}