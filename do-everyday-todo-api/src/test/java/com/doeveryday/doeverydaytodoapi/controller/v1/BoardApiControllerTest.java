package com.doeveryday.doeverydaytodoapi.controller.v1;

import com.doeveryday.doeverydaytodoapi.api.v1.model.BoardDTO;
import com.doeveryday.doeverydaytodoapi.services.BoardApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BoardApiControllerTest {

    //    Board values
    public static final Long BOARD_ID = 1L;
    public static final String COLOR_HEX = "#ffffff";
    public static final String BOARD_NAME = "Shop list";

    @Mock
    BoardApiService boardApiService;

    @InjectMocks
    BoardApiController boardApiController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(boardApiController).build();
    }

    @Test
    void getAllBoards() throws Exception {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setColorHex(COLOR_HEX);
        boardDTO.setName(BOARD_NAME);
        boardDTO.setId(BOARD_ID);

        when(boardApiService.getAllBoards()).thenReturn(Collections.singletonList(boardDTO));

        mockMvc.perform(get(BoardApiController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.boards", hasSize(1)));
    }

    @Test
    void getBoardById() throws Exception {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setColorHex(COLOR_HEX);
        boardDTO.setName(BOARD_NAME);
        boardDTO.setId(BOARD_ID);

        when(boardApiService.getBoardById(anyLong())).thenReturn(boardDTO);

        mockMvc.perform(get(BoardApiController.BASE_URL + "/" + BOARD_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(BOARD_NAME)))
                .andExpect(jsonPath("$.color_hex", equalTo(COLOR_HEX)))
                .andExpect(jsonPath("$.id", equalTo(BOARD_ID.intValue())));
    }

    @Test
    void postBoardById() throws Exception {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setColorHex(COLOR_HEX);
        boardDTO.setName(BOARD_NAME);
        boardDTO.setId(BOARD_ID);

        when(boardApiService.createBoard(any(BoardDTO.class))).thenReturn(boardDTO);

        mockMvc.perform(post(BoardApiController.BASE_URL, boardDTO)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ParserObjectToString.asJsonString(boardDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(BOARD_NAME)))
                .andExpect(jsonPath("$.color_hex", equalTo(COLOR_HEX)))
                .andExpect(jsonPath("$.id", equalTo(BOARD_ID.intValue())));
    }

    @Test
    void testPutBoardWithOneArg() throws Exception {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setColorHex(COLOR_HEX);
        boardDTO.setName(BOARD_NAME);
        boardDTO.setId(BOARD_ID);

        when(boardApiService.putBoard(any(BoardDTO.class), eq(BOARD_ID))).thenReturn(boardDTO);

        mockMvc.perform(put(BoardApiController.BASE_URL, boardDTO)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ParserObjectToString.asJsonString(boardDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(BOARD_NAME)))
                .andExpect(jsonPath("$.color_hex", equalTo(COLOR_HEX)))
                .andExpect(jsonPath("$.id", equalTo(BOARD_ID.intValue())));
    }

    @Test
    void testPutBoard() throws Exception {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setColorHex(COLOR_HEX);
        boardDTO.setName(BOARD_NAME);
        boardDTO.setId(BOARD_ID);

        when(boardApiService.putBoard(any(BoardDTO.class), eq(BOARD_ID))).thenReturn(boardDTO);

        mockMvc.perform(put(BoardApiController.BASE_URL + "/" + BOARD_ID, boardDTO)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ParserObjectToString.asJsonString(boardDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(BOARD_NAME)))
                .andExpect(jsonPath("$.color_hex", equalTo(COLOR_HEX)))
                .andExpect(jsonPath("$.id", equalTo(BOARD_ID.intValue())));
    }

    @Test
    void deleteBoard() throws Exception {
        boardApiController.deleteBoard(BOARD_ID);

        verify(boardApiService, times(1)).deleteBoardById(anyLong());

        mockMvc.perform(delete(BoardApiController.BASE_URL + "/" + BOARD_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}