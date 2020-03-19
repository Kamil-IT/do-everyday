package com.doeveryday.doeverydaytodoapi.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriorityDTO {

    private String name;
    @JsonProperty("bootstrap_class")
    private String bootstrapClass;

}
