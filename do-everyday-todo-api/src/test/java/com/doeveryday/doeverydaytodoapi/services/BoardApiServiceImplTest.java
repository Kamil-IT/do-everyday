package com.doeveryday.doeverydaytodoapi.services;

import com.doeveryday.doeverydaytodo.models.Board;
import com.doeveryday.doeverydaytodo.models.Task;
import com.doeveryday.doeverydaytodo.repository.BoardRepository;
import com.doeveryday.doeverydaytodo.repository.TaskRepository;
import com.doeveryday.doeverydaytodoapi.api.v1.mapper.*;
import com.doeveryday.doeverydaytodoapi.api.v1.model.BoardDTO;
import com.doeveryday.doeverydaytodoapi.api.v1.model.TaskDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {BoardMapperImpl.class, TaskMapperImpl.class})
class BoardApiServiceImplTest {

    //    Board values
    static final long BOARD_ID = 1L;
    static final String COLOR_HEX = "#ffffff";
    static final String BOARD_NAME = "Shop list";
    //    Task values
    static final long TASK_ID = 2L;
    static final String TASK_NAME = "Task name";
    static final String TASK_DESCRIPTION = "Example description";

    @Mock
    BoardRepository boardRepository;
    @Mock
    TaskRepository taskRepository;

    @Autowired
    BoardMapper boardMapper;
    @Autowired
    TaskMapper taskMapper;

    BoardApiService boardApiService;

    @BeforeEach
    void setUp() {
        boardApiService = new BoardApiServiceImpl(boardRepository, boardMapper, taskMapper, taskRepository);
    }

    @Test
    void getAllBoards() {
        when(boardRepository.findAll()).thenReturn(Arrays.asList(new Board(), new Board(), new Board()));

        List<BoardDTO> allBoards = boardApiService.getAllBoards();
        assertEquals(3, allBoards.size());
    }

    @Test
    void getBoardById() {
        Board board = new Board();
        board.setId(BOARD_ID);
        board.setColorHex(COLOR_HEX);
        board.setName(BOARD_NAME);

        Task task = new Task();
        task.setId(TASK_ID);
        task.setBoard(board);
        task.setDescription(TASK_DESCRIPTION);
        task.setName(TASK_NAME);

        board.setTasks(Collections.singletonList(task));

        when(boardRepository.findById(anyLong())).thenReturn(Optional.of(board));

        BoardDTO boardDTO = boardApiService.getBoardById(anyLong());

        assertEquals(BOARD_ID, boardDTO.getId());
        assertEquals(COLOR_HEX, boardDTO.getColorHex());
        assertEquals(BOARD_NAME, boardDTO.getName());

        Optional<TaskDTO> taskDTO = boardDTO.getTasks().stream().findFirst();
        assertTrue(taskDTO.isPresent());
        assertEquals(TASK_ID, taskDTO.get().getId());
        assertEquals(TASK_NAME, taskDTO.get().getName());
        assertEquals(BOARD_ID, taskDTO.get().getBoardId());
        assertEquals(TASK_DESCRIPTION, taskDTO.get().getDescription());
    }

    @Test
    void createBoard() {
        Board board = new Board();
        board.setId(BOARD_ID);
        board.setColorHex(COLOR_HEX);
        board.setName(BOARD_NAME);

        Task task = new Task();
        task.setId(TASK_ID);
        task.setBoard(board);
        task.setDescription(TASK_DESCRIPTION);
        task.setName(TASK_NAME);

        board.setTasks(Collections.singletonList(task));

        when(boardRepository.save(any(Board.class))).thenReturn(board);

        BoardDTO boardDTO = boardApiService.createBoard(boardMapper.boardToBoardDTO(board));

        assertEquals(BOARD_ID, boardDTO.getId());
        assertEquals(COLOR_HEX, boardDTO.getColorHex());
        assertEquals(BOARD_NAME, boardDTO.getName());

        Optional<TaskDTO> taskDTO = boardDTO.getTasks().stream().findFirst();
        assertTrue(taskDTO.isPresent());
        assertEquals(TASK_ID, taskDTO.get().getId());
        assertEquals(TASK_NAME, taskDTO.get().getName());
        assertEquals(BOARD_ID, taskDTO.get().getBoardId());
        assertEquals(TASK_DESCRIPTION, taskDTO.get().getDescription());
    }

    @Test
    void deleteBoardById() {

        when(boardRepository.existsById(anyLong())).thenReturn(true);

        boardApiService.deleteBoardById(anyLong());

        verify(boardRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void putBoard() {
        Board board = new Board();
        board.setId(BOARD_ID);
        board.setColorHex(COLOR_HEX);
        board.setName(BOARD_NAME);

        Task task = new Task();
        task.setId(TASK_ID);
        task.setBoard(board);
        task.setDescription(TASK_DESCRIPTION);
        task.setName(TASK_NAME);

        board.setTasks(Collections.singletonList(task));

        when(boardRepository.save(any(Board.class))).thenReturn(board);
        when(boardRepository.existsById(anyLong())).thenReturn(true);

        BoardDTO boardDTO = boardApiService.putBoard(boardMapper.boardToBoardDTO(board), board.getId());

        assertEquals(BOARD_ID, boardDTO.getId());
        assertEquals(COLOR_HEX, boardDTO.getColorHex());
        assertEquals(BOARD_NAME, boardDTO.getName());

        Optional<TaskDTO> taskDTO = boardDTO.getTasks().stream().findFirst();
        assertTrue(taskDTO.isPresent());
        assertEquals(TASK_ID, taskDTO.get().getId());
        assertEquals(TASK_NAME, taskDTO.get().getName());
        assertEquals(BOARD_ID, taskDTO.get().getBoardId());
        assertEquals(TASK_DESCRIPTION, taskDTO.get().getDescription());
    }
}