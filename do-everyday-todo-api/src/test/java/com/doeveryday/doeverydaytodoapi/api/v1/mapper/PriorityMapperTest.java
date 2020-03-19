package com.doeveryday.doeverydaytodoapi.api.v1.mapper;

import com.doeveryday.doeverydaytodo.models.Priority;
import com.doeveryday.doeverydaytodoapi.api.v1.model.PriorityDTO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PriorityMapperTest {

    PriorityMapper priorityMapper = Mappers.getMapper(PriorityMapper.class);

    @Test
    void priorityToPriorityDTO() {
        Priority important = Priority.IMPORTANT;

        PriorityDTO priorityDTO = priorityMapper.priorityToPriorityDTO(important);

        assertEquals(important.label, priorityDTO.getBootstrapClass());
        assertEquals(important.name(), priorityDTO.getName());
    }
}