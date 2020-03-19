package com.doeveryday.doeverydaytodoapi.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskManagerDTO {

    private Long id;

    @JsonProperty("task_id")
    private Long taskId;

    @JsonProperty("is_done")
    private boolean isDone;
    private String priority;

}
