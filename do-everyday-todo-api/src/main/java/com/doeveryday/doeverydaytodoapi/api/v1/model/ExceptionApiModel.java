package com.doeveryday.doeverydaytodoapi.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionApiModel {

    private String name;
    private String message;
    private short httpStatus;
}
