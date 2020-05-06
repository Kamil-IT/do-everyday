package com.doeveryday.doeverydaytodoapi.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskManagerListDTO {

    private List<TaskManagerDTO> taskManagers;
}
