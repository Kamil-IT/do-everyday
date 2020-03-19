package com.doeveryday.doeverydaytodoapi.services;

import com.doeveryday.doeverydaytodoapi.api.v1.model.PriorityDTO;

public interface PriorityApiService {

    PriorityDTO getPriorityByName(String name);

    PriorityDTO[] getAllPriority();
}
