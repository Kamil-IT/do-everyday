package com.doeveryday.doeverydaytodoapi.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Long id;

    private String appUserId;

    @NotBlank
    private String name;

    @JsonProperty("color_hex")
    private String colorHex;

    private List<TaskDTO> tasks = new ArrayList<>();
}
