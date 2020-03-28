package com.doeveryday.doeverydaytodoapi.services;

import com.doeveryday.doeverydaytodoapi.api.v1.model.BoardDTO;
import javassist.NotFoundException;

import java.util.List;

public interface BoardApiService {

    List<BoardDTO> getAllBoards();

    BoardDTO getBoardById(Long id) throws NotFoundException;

    BoardDTO createBoard(BoardDTO boardDTO);

    void deleteBoardById(Long id) throws NotFoundException;

    BoardDTO putBoard(BoardDTO boardDTO, Long id) throws NotFoundException;
}
