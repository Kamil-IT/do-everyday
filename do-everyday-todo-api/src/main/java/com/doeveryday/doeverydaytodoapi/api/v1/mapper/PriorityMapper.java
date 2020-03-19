package com.doeveryday.doeverydaytodoapi.api.v1.mapper;

import com.doeveryday.doeverydaytodo.models.Priority;
import com.doeveryday.doeverydaytodoapi.api.v1.model.PriorityDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PriorityMapper {

    @Named("priorityToPriorityDTO")
    default PriorityDTO priorityToPriorityDTO(Priority priority){
        PriorityDTO priorityDTO = new PriorityDTO();
        priorityDTO.setName(priority.name());
        priorityDTO.setBootstrapClass(priority.label);
        return priorityDTO;
    }
}
