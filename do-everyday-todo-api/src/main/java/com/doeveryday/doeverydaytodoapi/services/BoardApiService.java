package com.doeveryday.doeverydaytodoapi.services;

import com.doeveryday.doeverydaytodoapi.api.v1.model.BoardDTO;

import java.util.List;

public interface BoardApiService {

    List<BoardDTO> getAllBoards();

    BoardDTO getBoardById(Long id);

    BoardDTO createBoard(BoardDTO boardDTO);

    void deleteBoardById(Long id);

    BoardDTO putBoard(BoardDTO boardDTO, Long id);
}
