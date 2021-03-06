package com.doeveryday.doeverydaytodoapi.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    private Long id;

    @NotBlank
    private String name;
    private String description;

    @JsonProperty("create_date")
    private Date createDate;

    @JsonProperty("board_id")
    private Long boardId;
    private Long taskManagerId;

}
