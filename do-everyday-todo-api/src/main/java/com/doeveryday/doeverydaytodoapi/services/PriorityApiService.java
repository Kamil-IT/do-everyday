package com.doeveryday.doeverydaytodoapi.services;

import com.doeveryday.doeverydaytodoapi.api.v1.model.PriorityDTO;
import javassist.NotFoundException;

public interface PriorityApiService {

    PriorityDTO getPriorityByName(String name) throws NotFoundException;

    PriorityDTO[] getAllPriority();
}
