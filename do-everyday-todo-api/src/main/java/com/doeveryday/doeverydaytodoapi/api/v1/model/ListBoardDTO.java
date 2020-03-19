package com.doeveryday.doeverydaytodoapi.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ListBoardDTO {

    List<BoardDTO> boards;
}
