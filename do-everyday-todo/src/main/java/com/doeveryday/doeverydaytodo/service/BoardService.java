package com.doeveryday.doeverydaytodo.service;

import com.doeveryday.doeverydaytodo.models.Board;

import java.util.List;

public interface BoardService {
    /**
     * Boards with out any owner
     */
    Board saveBord(Board board);

    List<Board> getBoards();

    Board findById(Long id);

    void deleteById(Long id);

    void updateBoard(Board board);

    boolean existsById(Long id);

    /**
     * Boards with owners
     */

    List<Board> getBoards(String user_id);

    Board saveBord(Board board, String user_id);
}
