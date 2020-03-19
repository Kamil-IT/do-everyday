package com.doeveryday.doeverydaytodoapi.services;

import com.doeveryday.doeverydaytodo.models.Priority;
import com.doeveryday.doeverydaytodoapi.api.v1.mapper.PriorityMapper;
import com.doeveryday.doeverydaytodoapi.api.v1.model.PriorityDTO;
import org.springframework.stereotype.Service;

@Service
public class PriorityApiServiceImpl implements PriorityApiService {

    private final PriorityMapper priorityMapper;

    public PriorityApiServiceImpl(PriorityMapper priorityMapper) {
        this.priorityMapper = priorityMapper;
    }

    @Override
    public PriorityDTO getPriorityByName(String name) {
        return priorityMapper.priorityToPriorityDTO(Priority.valueOf(name));
    }

    @Override
    public PriorityDTO[] getAllPriority() {
        int countPriorities = Priority.values().length;
        PriorityDTO[] priorityDTOs = new PriorityDTO[countPriorities];
        for (int i = 0; i < countPriorities; i++) {
            priorityDTOs[i] = priorityMapper.priorityToPriorityDTO(Priority.values()[i]);
        }
        return priorityDTOs;
    }
}
