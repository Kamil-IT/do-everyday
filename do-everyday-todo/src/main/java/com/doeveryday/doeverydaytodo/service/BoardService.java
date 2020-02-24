package com.doeveryday.doeverydaytodo.service;

import com.doeveryday.doeverydaytodo.models.Board;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BoardService {
    Board saveBord(Board board);

    List<Board> getBoards();

    Board findById(Long id);

    void deleteById(Long id);

    void updateBoard(Board board);
}
