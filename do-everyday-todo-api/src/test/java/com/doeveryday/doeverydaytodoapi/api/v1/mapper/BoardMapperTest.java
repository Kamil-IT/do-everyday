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
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {BoardMapperImpl.class, TaskMapperImpl.class})
class BoardMapperTest {

    @Autowired
    private BoardMapper boardMapper;

//    Board value
    public static final long BOARD_ID = 1L;
    public static final String COLOR_HEX = "#ffffff";
    public static final String BOARD_NAME = "Shop list";
//    Task value
    public static final long TASK_ID = 2L;
    public static final Date TASK_CREATE_DATE = new Date();
    public static final String DESCRIPTION_TASK = "Example task";
    public static final String TASK_NAME = "Task name";
    public static final long TASK_MANAGER_ID = 3L;


    private void initDataDTO(BoardDTO boardDTO, TaskDTO taskDTO) {
        //        Set board
        boardDTO.setId(BOARD_ID);
        boardDTO.setColorHex(COLOR_HEX);
        boardDTO.setName(BOARD_NAME);

//        Set task witch belongs to board with id = 1
        taskDTO.setId(TASK_ID);
        taskDTO.setBoardId(BOARD_ID);
        taskDTO.setCreateDate(TASK_CREATE_DATE);
        taskDTO.setDescription(DESCRIPTION_TASK);
        taskDTO.setName(TASK_NAME);
        taskDTO.setTaskManagerId(TASK_MANAGER_ID);
    }

    private void initDataEntity(Board board, Task task) {
        //        Set board
        board.setId(BOARD_ID);
        board.setColorHex(COLOR_HEX);
        board.setName(BOARD_NAME);

//        Set task witch belongs to board with id = 1
        task.setId(TASK_ID);
        task.setBoard(board);
        task.setCreateDate(TASK_CREATE_DATE);
        task.setDescription(DESCRIPTION_TASK);
        task.setName(TASK_NAME);
        TaskManager taskManager = new TaskManager();
        taskManager.setId(TASK_MANAGER_ID);
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