package com.doeveryday.doeverydaytodo.service;

import com.doeveryday.doeverydaysecurity.repository.AppUserRepository;
import com.doeveryday.doeverydaytodo.models.Board;
import com.doeveryday.doeverydaytodo.models.Task;
import com.doeveryday.doeverydaytodo.repository.BoardRepository;
import com.doeveryday.doeverydaytodo.repository.TaskMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class BoardServiceTest {

    static final String COLOR_HEX = "#ffffff";
    static final String BOARD_NAME = "Example board";
    static final long BOARD_ID = 1L;

    @Mock
    BoardRepository boardRepository;

    @Mock
    TaskMemberRepository taskMemberRepository;

    @Mock
    AppUserRepository appUserRepository;

    BoardService boardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        boardService = new BoardServiceImpl(boardRepository, taskMemberRepository, appUserRepository);
    }

    @Test
    void saveBord() {
        Board board = new Board();
        board.setId(1L);

        when(boardRepository.save(any(Board.class))).thenReturn(board);
        when(boardRepository.existsById(anyLong())).thenReturn(true);

        boardService.updateBoard(board);

        verify(boardRepository, times(1)).save(any(Board.class));
    }

    @Test
    void getBoards() {
        List<Board> boards = Arrays.asList(new Board(), new Board(), new Board());

        when(boardRepository.findAll()).thenReturn(boards);

        assertEquals(3, boardService.getBoards().size());
    }

    @Test
    void findById() {
        Board board = new Board();
        board.setId(BOARD_ID);
        board.setName(BOARD_NAME);
        board.setColorHex(COLOR_HEX);
        board.setTasks(Arrays.asList(new Task(), new Task()));

        when(boardRepository.findById(anyLong())).thenReturn(Optional.of(board));

        assertEquals(board, boardService.findById(anyLong()));
    }

    @Test
    void deleteById() {
        Long id = BOARD_ID;

        boardRepository.deleteById(id);

        verify(boardRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void updateBoard() {
        Board board = new Board();
        board.setId(BOARD_ID);
        board.setName(BOARD_NAME);
        board.setColorHex(COLOR_HEX);
        board.setTasks(Arrays.asList(new Task(), new Task()));

        when(boardRepository.save(any(Board.class))).thenReturn(board);

        Board boardSaved = boardService.saveBord(board);

        assertEquals(BOARD_ID, boardSaved.getId());
        assertEquals(BOARD_NAME, boardSaved.getName());
        assertEquals(COLOR_HEX, boardSaved.getColorHex());
        assertEquals(board.getTasks().size(), board.getTasks().size());
    }

    @Test
    void existsById() {

        when(boardRepository.existsById(anyLong())).thenReturn(true);

        assertTrue(boardService.existsById(anyLong()));
        verify(boardRepository, times(1)).existsById(anyLong());
    }
}