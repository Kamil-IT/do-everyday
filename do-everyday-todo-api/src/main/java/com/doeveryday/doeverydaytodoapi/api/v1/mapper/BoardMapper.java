package com.doeveryday.doeverydaytodoapi.api.v1.mapper;

import com.doeveryday.doeverydaytodo.models.Board;
import com.doeveryday.doeverydaytodo.models.Task;
import com.doeveryday.doeverydaytodoapi.api.v1.model.BoardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(uses = TaskMapper.class, componentModel = "spring")
public interface BoardMapper {


    @Mapping(source = "tasks", target = "tasks", qualifiedByName = "TasksDTOToTasks")
    Board boardDTOToBoard(BoardDTO boardDTO);

    @Mapping(source = "tasks", target = "tasks", qualifiedByName = "tasksToTasksDTO")
    BoardDTO boardToBoardDTO(Board board);
}
