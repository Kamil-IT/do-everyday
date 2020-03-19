package com.doeveryday.doeverydaytodoapi.api.v1.mapper;

import com.doeveryday.doeverydaytodo.models.Board;
import com.doeveryday.doeverydaytodo.models.Task;
import com.doeveryday.doeverydaytodo.models.TaskManager;
import com.doeveryday.doeverydaytodoapi.api.v1.model.BoardDTO;
import com.doeveryday.doeverydaytodoapi.api.v1.model.TaskDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {BoardMapperImpl.class, TaskMapperImpl.class})
class BoardMapperTest {

    @Autowired
    private BoardMapper boardMapper;


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
    void boardDTOToBoard() {
        BoardDTO boardDTO = new BoardDTO();
        TaskDTO taskDTO = new TaskDTO();
        initDataDTO(boardDTO, taskDTO);

        Board board = boardMapper.boardDTOToBoard(boardDTO);

        assertEquals(board.getName(), boardDTO.getName());
        assertEquals(board.getColorHex(), boardDTO.getColorHex());
        assertEquals(board.getId(), boardDTO.getId());
    }

    @Test
    void boardDTOToBoard_tasksConversion(){
        BoardDTO boardDTO = new BoardDTO();
        TaskDTO taskDTO = new TaskDTO();
        initDataDTO(boardDTO, taskDTO);

        List<TaskDTO> taskDTOs = new ArrayList<>();
        taskDTOs.add(taskDTO);
        boardDTO.setTasks(taskDTOs);

        Board board = boardMapper.boardDTOToBoard(boardDTO);

        Task task = board.getTasks().stream().findFirst().get();

        assertEquals(taskDTO.getId(), task.getId());
        assertEquals(taskDTO.getCreateDate(), task.getCreateDate());
        assertEquals(taskDTO.getDescription(), task.getDescription());
        assertEquals(taskDTO.getName(), task.getName());
    }

    @Test
    void boardToBoardDTO() {
        Board board = new Board();
        Task task = new Task();
        initDataEntity(board, task);

        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        board.setTasks(tasks);

        BoardDTO boardDTO = boardMapper.boardToBoardDTO(board);

        assertEquals(board.getId(), boardDTO.getId());
        assertEquals(board.getName(), boardDTO.getName());
        assertEquals(board.getColorHex(), boardDTO.getColorHex());
    }

    @Test
    void boardToBoardDTO_tasksConversion() {
        Board board = new Board();
        Task task = new Task();
        initDataEntity(board, task);

        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        board.setTasks(tasks);

        BoardDTO boardDTO = boardMapper.boardToBoardDTO(board);

        TaskDTO taskDTO = boardDTO.getTasks().stream().findFirst().get();

        assertEquals(taskDTO.getId(), task.getId());
        assertEquals(taskDTO.getCreateDate(), task.getCreateDate());
        assertEquals(taskDTO.getDescription(), task.getDescription());
        assertEquals(taskDTO.getName(), task.getName());
        assertEquals(taskDTO.getBoardId(), task.getBoard().getId());
        assertEquals(taskDTO.getTaskManagerId(), task.getTaskManager().getId());
    }
}