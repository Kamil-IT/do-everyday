package com.doeveryday.doeverydaytodoapi.services;

import com.doeveryday.doeverydaytodo.models.Priority;
import com.doeveryday.doeverydaytodo.models.Task;
import com.doeveryday.doeverydaytodo.models.TaskManager;
import com.doeveryday.doeverydaytodo.models.TaskMember;
import com.doeveryday.doeverydaytodo.repository.TaskManagerRepository;
import com.doeveryday.doeverydaytodo.repository.TaskMemberRepository;
import com.doeveryday.doeverydaytodoapi.api.v1.mapper.TaskManagerMapper;
import com.doeveryday.doeverydaytodoapi.api.v1.mapper.TaskManagerMapperImpl;
import com.doeveryday.doeverydaytodoapi.api.v1.model.TaskManagerDTO;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = TaskManagerMapperImpl.class)
class TaskManagerApiServiceImplTest {

    static final long TASK_MANAGER_ID = 1L;
    static final boolean TASK_MANAGER_IS_DONE = true;
    static final long TASK_ID = 2L;
    static final Priority TASK_MEMBER_IMPORTANT = Priority.IMPORTANT;
    @Mock
    TaskManagerRepository taskManagerRepository;

    @Mock
    TaskMemberRepository taskMemberRepository;

    @Autowired
    TaskManagerMapper taskManagerMapper;

    TaskManagerApiService taskManagerApiService;

    @BeforeEach
    void setUp() {
        taskManagerApiService = new TaskManagerApiServiceImpl(taskManagerRepository, taskManagerMapper, taskMemberRepository);
    }

    @Test
    void getAllTaskManager() {
        when(taskManagerRepository.findAll()).thenReturn(Arrays.asList(new TaskManager(), new TaskManager(), new TaskManager()));

        assertEquals(3, taskManagerApiService.getAllTaskManager().size());
        verify(taskManagerRepository, times(1)).findAll();
    }

    @Test
    void getTaskManagerById() throws NotFoundException {
        TaskManager taskManager = new TaskManager();
        taskManager.setId(TASK_MANAGER_ID);
        taskManager.setDone(TASK_MANAGER_IS_DONE);
        taskManager.setPriority(TASK_MEMBER_IMPORTANT);

        Task task = new Task();
        task.setId(TASK_ID);

        taskManager.setTask(task);
        taskManager.setTaskMember(Arrays.asList(new TaskMember() , new TaskMember()));

        when(taskManagerRepository.findById(anyLong())).thenReturn(Optional.of(taskManager));

        TaskManagerDTO taskManagerDTO = taskManagerApiService.getTaskManagerById(anyLong());

        assertEquals(TASK_MANAGER_ID, taskManagerDTO.getId());
        assertEquals(TASK_MANAGER_IS_DONE, taskManagerDTO.isDone());
        assertEquals(TASK_MEMBER_IMPORTANT.name(), taskManagerDTO.getPriority());
        assertEquals(TASK_ID, taskManagerDTO.getTaskId());
        assertEquals(2, taskManagerDTO.getTaskMembers().size());
    }

    @Test
    void createTaskManager() {
        TaskManager taskManager = new TaskManager();
        taskManager.setId(TASK_MANAGER_ID);
        taskManager.setDone(TASK_MANAGER_IS_DONE);
        taskManager.setPriority(TASK_MEMBER_IMPORTANT);

        Task task = new Task();
        task.setId(TASK_ID);

        taskManager.setTask(task);
        taskManager.setTaskMember(Arrays.asList(new TaskMember() , new TaskMember()));


        when(taskManagerRepository.save(any(TaskManager.class))).thenReturn(taskManager);

        TaskManagerDTO taskManagerDTO = taskManagerApiService
                .createTaskManager(
                        taskManagerMapper.taskManagerToTaskMangerDTO(taskManager));

        assertEquals(TASK_MANAGER_ID, taskManagerDTO.getId());
        assertEquals(TASK_MANAGER_IS_DONE, taskManagerDTO.isDone());
        assertEquals(TASK_MEMBER_IMPORTANT.name(), taskManagerDTO.getPriority());
        assertEquals(TASK_ID, taskManagerDTO.getTaskId());
        assertEquals(2, taskManagerDTO.getTaskMembers().size());
    }

    @Test
    void putTaskManager() throws NotFoundException {
        TaskManager taskManager = new TaskManager();
        taskManager.setId(TASK_MANAGER_ID);
        taskManager.setDone(TASK_MANAGER_IS_DONE);
        taskManager.setPriority(TASK_MEMBER_IMPORTANT);

        Task task = new Task();
        task.setId(TASK_ID);

        taskManager.setTask(task);
        taskManager.setTaskMember(Arrays.asList(new TaskMember() , new TaskMember()));


        when(taskManagerRepository.save(any(TaskManager.class))).thenReturn(taskManager);
        when(taskManagerRepository.existsById(anyLong())).thenReturn(true);

        TaskManagerDTO taskManagerDTO = taskManagerApiService
                .putTaskManager(
                        taskManagerMapper.taskManagerToTaskMangerDTO(taskManager), taskManager.getId());

        assertEquals(TASK_MANAGER_ID, taskManagerDTO.getId());
        assertEquals(TASK_MANAGER_IS_DONE, taskManagerDTO.isDone());
        assertEquals(TASK_MEMBER_IMPORTANT.name(), taskManagerDTO.getPriority());
        assertEquals(TASK_ID, taskManagerDTO.getTaskId());
        assertEquals(2, taskManagerDTO.getTaskMembers().size());
    }

    @Test
    void deleteTaskManager() {
        when(taskManagerRepository.existsById(anyLong())).thenReturn(true);

        taskManagerApiService.deleteTaskManager(TASK_MANAGER_ID);

        verify(taskManagerRepository, times(1)).deleteById(anyLong());
    }
}