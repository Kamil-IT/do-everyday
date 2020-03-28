package com.doeveryday.doeverydaytodoapi.services;

import com.doeveryday.doeverydaytodo.models.Priority;
import com.doeveryday.doeverydaytodoapi.api.v1.mapper.PriorityMapper;
import com.doeveryday.doeverydaytodoapi.api.v1.model.PriorityDTO;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class PriorityApiServiceImpl implements PriorityApiService {

    private final PriorityMapper priorityMapper;

    public PriorityApiServiceImpl(PriorityMapper priorityMapper) {
        this.priorityMapper = priorityMapper;
    }

    @Override
    public PriorityDTO getPriorityByName(String name) throws NotFoundException {
        Optional<Priority> priorityOptional = Arrays.stream(Priority.values()).filter(priority -> priority.name().equals(name)).findFirst();
        return priorityMapper.priorityToPriorityDTO(priorityOptional.orElseThrow(() -> new NotFoundException("Not found priority with name: " + name)));
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
