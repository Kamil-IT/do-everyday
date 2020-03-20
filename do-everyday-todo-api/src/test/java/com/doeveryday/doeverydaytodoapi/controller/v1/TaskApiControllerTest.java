package com.doeveryday.doeverydaytodoapi.controller.v1;

import com.doeveryday.doeverydaytodoapi.api.v1.model.TaskDTO;
import com.doeveryday.doeverydaytodoapi.services.TaskApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TaskApiControllerTest {

    //    Task values
    public static final Long TASK_ID = 2L;
    public static final long TASK2_ID = 3L;
    public static final Date TASK_CREATE_DATE = new Date();
    public static final String DESCRIPTION_TASK = "Example task";
    public static final String TASK_NAME = "Task name";
    public static final Long BOARD_ID = 5L;

    @Mock
    TaskApiService taskApiService;

    @InjectMocks
    TaskApiController taskApiController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(taskApiController).build();
    }

    @Test
    void getAllTasks() throws Exception {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(TASK_ID);
        taskDTO.setCreateDate(TASK_CREATE_DATE);
        taskDTO.setDescription(DESCRIPTION_TASK);
        taskDTO.setName(TASK_NAME);

        when(taskApiService.getAllTasks()).thenReturn(Arrays.asList(taskDTO, new TaskDTO(), new TaskDTO()));

        mockMvc.perform(get(TaskApiController.BASE_URL + "task")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tasks", hasSize(3)));
    }

    @Test
    void getAllTaskByBoard() throws Exception {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(TASK_ID);
        taskDTO.setBoardId(BOARD_ID);
        TaskDTO taskDTO2 = new TaskDTO();
        taskDTO2.setId(TASK2_ID);
        taskDTO2.setBoardId(BOARD_ID);

        when(taskApiService.getTaskByBoardId(anyLong())).thenReturn(Arrays.asList(taskDTO, taskDTO2));

        mockMvc.perform(get(TaskApiController.BASE_URL + "board/" + BOARD_ID + "/task")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tasks", hasSize(2)));
    }

    @Test
    void getTaskById() throws Exception {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(TASK_ID);
        taskDTO.setBoardId(BOARD_ID);
        taskDTO.setCreateDate(TASK_CREATE_DATE);
        taskDTO.setDescription(DESCRIPTION_TASK);
        taskDTO.setName(TASK_NAME);

        when(taskApiService.getTaskById(anyLong())).thenReturn(taskDTO);

        mockMvc.perform(get(TaskApiController.BASE_URL + "task/" + TASK_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(TASK_ID.intValue())))
                .andExpect(jsonPath("$.board_id", equalTo(BOARD_ID.intValue())))
                .andExpect(jsonPath("$.create_date", equalTo(TASK_CREATE_DATE.getTime())))
                .andExpect(jsonPath("$.description", equalTo(DESCRIPTION_TASK)))
                .andExpect(jsonPath("$.name", equalTo(TASK_NAME)));

    }

    @Test
    void createTask() throws Exception {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(TASK_ID);
        taskDTO.setBoardId(BOARD_ID);
        taskDTO.setCreateDate(TASK_CREATE_DATE);
        taskDTO.setDescription(DESCRIPTION_TASK);
        taskDTO.setName(TASK_NAME);

        when(taskApiService.createTask(any(TaskDTO.class))).thenReturn(taskDTO);

        mockMvc.perform(post(TaskApiController.BASE_URL + "task")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ParserObjectToString.asJsonString(taskDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(TASK_ID.intValue())))
                .andExpect(jsonPath("$.board_id", equalTo(BOARD_ID.intValue())))
                .andExpect(jsonPath("$.create_date", equalTo(TASK_CREATE_DATE.getTime())))
                .andExpect(jsonPath("$.description", equalTo(DESCRIPTION_TASK)))
                .andExpect(jsonPath("$.name", equalTo(TASK_NAME)));
    }

    @Test
    void putTaskById() throws Exception {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(TASK_ID);
        taskDTO.setBoardId(BOARD_ID);
        taskDTO.setCreateDate(TASK_CREATE_DATE);
        taskDTO.setDescription(DESCRIPTION_TASK);
        taskDTO.setName(TASK_NAME);

        when(taskApiService.putTask(any(TaskDTO.class), anyLong())).thenReturn(taskDTO);

        mockMvc.perform(put(TaskApiController.BASE_URL + "task/" + TASK_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ParserObjectToString.asJsonString(taskDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(TASK_ID.intValue())))
                .andExpect(jsonPath("$.board_id", equalTo(BOARD_ID.intValue())))
                .andExpect(jsonPath("$.create_date", equalTo(TASK_CREATE_DATE.getTime())))
                .andExpect(jsonPath("$.description", equalTo(DESCRIPTION_TASK)))
                .andExpect(jsonPath("$.name", equalTo(TASK_NAME)));

    }

    @Test
    void putTask() throws Exception {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(TASK_ID);
        taskDTO.setBoardId(BOARD_ID);
        taskDTO.setCreateDate(TASK_CREATE_DATE);
        taskDTO.setDescription(DESCRIPTION_TASK);
        taskDTO.setName(TASK_NAME);

        when(taskApiService.putTask(any(TaskDTO.class), anyLong())).thenReturn(taskDTO);

        mockMvc.perform(put(TaskApiController.BASE_URL + "task")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ParserObjectToString.asJsonString(taskDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(TASK_ID.intValue())))
                .andExpect(jsonPath("$.board_id", equalTo(BOARD_ID.intValue())))
                .andExpect(jsonPath("$.create_date", equalTo(TASK_CREATE_DATE.getTime())))
                .andExpect(jsonPath("$.description", equalTo(DESCRIPTION_TASK)))
                .andExpect(jsonPath("$.name", equalTo(TASK_NAME)));
    }

    @Test
    void deleteTask() throws Exception {

        taskApiController.deleteTask(anyLong());

        verify(taskApiService, times(1)).deleteTaskById(anyLong());

        mockMvc.perform(delete(TaskApiController.BASE_URL + "task/" + TASK_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}