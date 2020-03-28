package com.doeveryday.doeverydaytodoapi.services;

import com.doeveryday.doeverydaytodo.models.Board;
import com.doeveryday.doeverydaytodo.models.Task;
import com.doeveryday.doeverydaytodo.models.TaskManager;
import com.doeveryday.doeverydaytodo.repository.BoardRepository;
import com.doeveryday.doeverydaytodo.repository.TaskManagerRepository;
import com.doeveryday.doeverydaytodo.repository.TaskRepository;
import com.doeveryday.doeverydaytodoapi.api.v1.mapper.TaskMapper;
import com.doeveryday.doeverydaytodoapi.api.v1.mapper.TaskMapperImpl;
import com.doeveryday.doeverydaytodoapi.api.v1.model.TaskDTO;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = TaskMapperImpl.class)
class TaskApiServiceImplTest {

    static final long TASK_ID = 2L;
    static final Date TASK_CREATE_DATE = new Date();
    static final String TASK_NAME = "Task name";
    static final long TASK_MANAGER_ID = 3L;
    static final String TASK_DESCRIPTION = "Example description";
    static final Long BOARD_ID = 1L;

    @Mock
    TaskApiService taskApiService;

    @Mock
    BoardRepository boardRepository;
    @Mock
    TaskManagerRepository taskManagerRepository;
    @Mock
    TaskRepository taskRepository;

    @Autowired
    TaskMapper taskMapper;

    @BeforeEach
    void setUp() {
        taskApiService = new TaskApiServiceImpl(taskRepository, taskMapper, boardRepository, taskManagerRepository);
    }

    @Test
    void getAllTasks() {
        when(taskRepository.findAll()).thenReturn(Arrays.asList(new Task(), new Task(), new Task()));

        assertEquals(3, taskApiService.getAllTasks().size());
    }

    @Test
    void getTaskById() throws NotFoundException {
        Task task = new Task();
        task.setId(TASK_ID);

        task.setCreateDate(TASK_CREATE_DATE);
        task.setDescription(TASK_DESCRIPTION);
        task.setName(TASK_NAME);

        TaskManager taskManager = new TaskManager();
        taskManager.setId(TASK_MANAGER_ID);
        Board board = new Board();
        board.setId(BOARD_ID);

        task.setBoard(board);
        task.setTaskManager(taskManager);

        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(task));

        TaskDTO taskDTO = taskApiService.getTaskById(TASK_ID);

        assertEquals(TASK_ID, taskDTO.getId());
        assertEquals(TASK_CREATE_DATE, taskDTO.getCreateDate());
        assertEquals(TASK_DESCRIPTION, taskDTO.getDescription());
        assertEquals(TASK_NAME, taskDTO.getName());
        assertEquals(TASK_MANAGER_ID, taskDTO.getTaskManagerId());
        assertEquals(BOARD_ID, taskDTO.getBoardId());
    }

    @Test
    void createTask() throws NotFoundException {
        Task task = new Task();
        task.setId(TASK_ID);

        task.setCreateDate(TASK_CREATE_DATE);
        task.setDescription(TASK_DESCRIPTION);
        task.setName(TASK_NAME);

        TaskManager taskManager = new TaskManager();
        taskManager.setId(TASK_MANAGER_ID);
        Board board = new Board();
        board.setId(BOARD_ID);

        task.setBoard(board);
        task.setTaskManager(taskManager);

        when(taskRepository.save(any(Task.class))).thenReturn(task);
        when(boardRepository.findById(anyLong())).thenReturn(Optional.of(board));
        when(taskManagerRepository.findById(anyLong())).thenReturn(Optional.of(taskManager));

        TaskDTO taskDTO = taskApiService.createTask(taskMapper.taskToTaskDTO(task));

        assertEquals(TASK_ID, taskDTO.getId());
        assertEquals(TASK_CREATE_DATE, taskDTO.getCreateDate());
        assertEquals(TASK_DESCRIPTION, taskDTO.getDescription());
        assertEquals(TASK_NAME, taskDTO.getName());
        assertEquals(TASK_MANAGER_ID, taskDTO.getTaskManagerId());
        assertEquals(BOARD_ID, taskDTO.getBoardId());
    }

    @Test
    void deleteTaskById() throws NotFoundException {
        when(taskRepository.existsById(anyLong())).thenReturn(true);

        taskApiService.deleteTaskById(anyLong());

        verify(taskRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void putTask() throws NotFoundException {
        Task task = new Task();
        task.setId(TASK_ID);

        task.setCreateDate(TASK_CREATE_DATE);
        task.setDescription(TASK_DESCRIPTION);
        task.setName(TASK_NAME);

        TaskManager taskManager = new TaskManager();
        taskManager.setId(TASK_MANAGER_ID);
        Board board = new Board();
        board.setId(BOARD_ID);

        task.setBoard(board);
        task.setTaskManager(taskManager);

        when(taskRepository.save(any(Task.class))).thenReturn(task);
        when(taskRepository.existsById(anyLong())).thenReturn(true);
        when(boardRepository.findById(anyLong())).thenReturn(Optional.of(board));
        when(taskManagerRepository.findById(anyLong())).thenReturn(Optional.of(taskManager));

        TaskDTO taskDTO = taskApiService.putTask(taskMapper.taskToTaskDTO(task), task.getId());

        assertEquals(TASK_ID, taskDTO.getId());
        assertEquals(TASK_CREATE_DATE, taskDTO.getCreateDate());
        assertEquals(TASK_DESCRIPTION, taskDTO.getDescription());
        assertEquals(TASK_NAME, taskDTO.getName());
        assertEquals(TASK_MANAGER_ID, taskDTO.getTaskManagerId());
        assertEquals(BOARD_ID, taskDTO.getBoardId());
    }

    @Test
    void getTaskByBoardId() {
        Task task1 = new Task();
        Task task2 = new Task();
        Task task3 = new Task();

        Board board = new Board();
        board.setId(BOARD_ID);

        task1.setBoard(board);
        task2.setBoard(board);
        task3.setBoard(board);

        when(taskRepository.findAllByBoardId(anyLong())).thenReturn(Arrays.asList(task1, task2, task3));

        List<TaskDTO> taskDTOs = taskApiService.getTaskByBoardId(anyLong());

        for (TaskDTO taskDTO :
                taskDTOs) {
            assertEquals(BOARD_ID, taskDTO.getBoardId());
        }
        assertEquals(3, taskDTOs.size());
    }
}