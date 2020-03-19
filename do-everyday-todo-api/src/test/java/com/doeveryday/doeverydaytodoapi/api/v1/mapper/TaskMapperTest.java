package com.doeveryday.doeverydaytodoapi.api.v1.mapper;

import com.doeveryday.doeverydaytodo.models.Board;
import com.doeveryday.doeverydaytodo.models.Task;
import com.doeveryday.doeverydaytodo.models.TaskManager;
import com.doeveryday.doeverydaytodoapi.api.v1.model.BoardDTO;
import com.doeveryday.doeverydaytodoapi.api.v1.model.TaskDTO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskMapperTest {

    TaskMapper taskMapper = Mappers.getMapper(TaskMapper.class);


    private void initDataDTO(BoardDTO boardDTO, TaskDTO taskDTO) {
        //        Set board
        boardDTO.setId(ConstValues.BOARD_ID);
        boardDTO.setColorHex(ConstValues.COLOR_HEX);
        boardDTO.setName(ConstValues.BOARD_NAME);

//        Set task witch belongs to board with id = 1
        taskDTO.setId(ConstValues.TASK_ID);
        taskDTO.setBoardId(ConstValues.BOARD_ID);
        taskDTO.setCreateDate(ConstValues.TASK_CREATE_DATE);
        taskDTO.setDescription(ConstValues.DESCRIPTION_TASK);
        taskDTO.setName(ConstValues.TASK_NAME);
        taskDTO.setTaskManagerId(ConstValues.TASK_MANAGER_ID);
    }

    private void initDataEntity(Board board, Task task) {
        //        Set board
        board.setId(ConstValues.BOARD_ID);
        board.setColorHex(ConstValues.COLOR_HEX);
        board.setName(ConstValues.BOARD_NAME);

//        Set task witch belongs to board with id = 1
        task.setId(ConstValues.TASK_ID);
        task.setBoard(board);
        task.setCreateDate(ConstValues.TASK_CREATE_DATE);
        task.setDescription(ConstValues.DESCRIPTION_TASK);
        task.setName(ConstValues.TASK_NAME);
        TaskManager taskManager = new TaskManager();
        taskManager.setId(ConstValues.TASK_MANAGER_ID);
        task.setTaskManager(taskManager);
    }

    @Test
    void taskToTaskDTO() {
        Task task = new Task();
        Board board = new Board();
        initDataEntity(board, task);

        TaskDTO taskDTO = taskMapper.taskToTaskDTO(task);

        assertEquals(task.getId(), taskDTO.getId());
        assertEquals(task.getName(), taskDTO.getName());
        assertEquals(task.getCreateDate(), taskDTO.getCreateDate());
    }

    @Test
    void taskDTOToTask() {
        TaskDTO taskDTO = new TaskDTO();
        BoardDTO boardDTO = new BoardDTO();
        initDataDTO(boardDTO, taskDTO);

        Task task = taskMapper.taskDTOToTask(taskDTO);

        assertEquals(taskDTO.getId(), taskDTO.getId());
        assertEquals(taskDTO.getName(), taskDTO.getName());
        assertEquals(taskDTO.getCreateDate(), taskDTO.getCreateDate());
    }


    @Test
    void tasksToTasksDTO() {
        Task task = new Task();
        Board board = new Board();
        initDataEntity(board, task);

        List<Task> tasks = new ArrayList<>();
        tasks.add(task);

        taskMapper.tasksToTasksDTO(tasks);
        assertEquals(1, tasks.size());
    }
}