package com.doeveryday.doeverydaytodoapi.controller.v1;

import com.doeveryday.doeverydaytodo.models.TaskManager;
import com.doeveryday.doeverydaytodoapi.api.v1.model.TaskManagerDTO;
import com.doeveryday.doeverydaytodoapi.services.TaskManagerApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TaskManagerApiControllerTest {

    private static final String IMPORTANT = "IMPORTANT";
    private static final Long TASK_MANAGER_ID = 1L;
    @Mock
    TaskManagerApiService taskManagerApiService;

    @InjectMocks
    TaskManagerApiController taskManagerApiController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(taskManagerApiController).build();
    }

    @Test
    void getAllTaskManager() throws Exception {
        when(taskManagerApiService.getAllTaskManager()).thenReturn(Arrays.asList(new TaskManagerDTO(), new TaskManagerDTO(), new TaskManagerDTO()));

        mockMvc.perform(get(TaskManagerApiController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.taskManagers", hasSize(3)));
    }

    @Test
    void getTaskManagerById() throws Exception {
        TaskManagerDTO taskManagerDTO = new TaskManagerDTO();
        taskManagerDTO.setId(TASK_MANAGER_ID);
        taskManagerDTO.setDone(true);
        taskManagerDTO.setPriority(IMPORTANT);

        when(taskManagerApiService.getTaskManagerById(anyLong())).thenReturn(taskManagerDTO);

        mockMvc.perform(get(TaskManagerApiController.BASE_URL + "/" + TASK_MANAGER_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(TASK_MANAGER_ID.intValue())))
                .andExpect(jsonPath("$.is_done", equalTo(true)))
                .andExpect(jsonPath("$.priority", equalTo(IMPORTANT)));

    }

    @Test
    void createTaskManager() throws Exception {
        TaskManagerDTO taskManagerDTO = new TaskManagerDTO();
        taskManagerDTO.setId(TASK_MANAGER_ID);
        taskManagerDTO.setDone(true);
        taskManagerDTO.setPriority(IMPORTANT);

        when(taskManagerApiService.createTaskManager(any(TaskManagerDTO.class))).thenReturn(taskManagerDTO);

        mockMvc.perform(post(TaskManagerApiController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(TASK_MANAGER_ID.intValue())))
                .andExpect(jsonPath("$.is_done", equalTo(true)))
                .andExpect(jsonPath("$.priority", equalTo(IMPORTANT)));
    }

    @Test
    void putTaskManager() throws Exception {
        TaskManagerDTO taskManagerDTO = new TaskManagerDTO();
        taskManagerDTO.setId(TASK_MANAGER_ID);
        taskManagerDTO.setDone(true);
        taskManagerDTO.setPriority(IMPORTANT);

        when(taskManagerApiService.putTaskManager(any(TaskManagerDTO.class), anyLong())).thenReturn(taskManagerDTO);

        mockMvc.perform(put(TaskManagerApiController.BASE_URL + "/" + TASK_MANAGER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ParserObjectToString.asJsonString(taskManagerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(TASK_MANAGER_ID.intValue())))
                .andExpect(jsonPath("$.is_done", equalTo(true)))
                .andExpect(jsonPath("$.priority", equalTo(IMPORTANT)));
    }

    @Test
    void deleteTaskManager() throws Exception {

        taskManagerApiController.deleteTaskManager(TASK_MANAGER_ID);

        verify(taskManagerApiService, times(1)).deleteTaskManager(anyLong());

        mockMvc.perform(delete(TaskManagerApiController.BASE_URL + "/" + TASK_MANAGER_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}