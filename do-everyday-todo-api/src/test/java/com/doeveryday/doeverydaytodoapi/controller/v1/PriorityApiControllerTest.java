package com.doeveryday.doeverydaytodoapi.controller.v1;

import com.doeveryday.doeverydaytodoapi.api.v1.model.PriorityDTO;
import com.doeveryday.doeverydaytodoapi.services.PriorityApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PriorityApiControllerTest {

    @Mock
    PriorityApiService priorityApiService;

    @InjectMocks
    PriorityApiController priorityApiController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(priorityApiController).build();
    }

    @Test
    void getAllPriorities() throws Exception {
        PriorityDTO[] priorities = new PriorityDTO[2];
        priorities[0] = new PriorityDTO("Important", "class");
        priorities[1] = new PriorityDTO("Normal", "class2");

        when(priorityApiService.getAllPriority()).thenReturn(priorities);

        mockMvc.perform(get(PriorityApiController.BASE_URL)
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getPrioritiesByName() throws Exception {
        PriorityDTO priority = new PriorityDTO("important", "class");

        when(priorityApiService.getPriorityByName(anyString())).thenReturn(priority);

        mockMvc.perform(get(PriorityApiController.BASE_URL + "/important")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("important")))
                .andExpect(jsonPath("$.bootstrap_class", equalTo("class")));
    }
}