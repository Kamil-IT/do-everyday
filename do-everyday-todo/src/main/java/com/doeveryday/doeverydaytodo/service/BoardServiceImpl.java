package com.doeveryday.doeverydaytodo.service;

import com.doeveryday.doeverydaytodo.exceptions.NotFoundException;
import com.doeveryday.doeverydaytodo.models.Board;
import com.doeveryday.doeverydaytodo.repository.BoardRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public Board saveBord(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public List<Board> getBoards() {
        List<Board> boards = new ArrayList<>();
        boardRepository.findAll().forEach(boards::add);
        return boards;
    }

    @Override
    public Board findById(Long id) {
        Optional<Board> boardOptional = boardRepository.findById(id);
        if (boardOptional.isPresent()){
            return boardOptional.get();
        }
        else {
            log.error("Not found board with id: " + id);
            throw new NotFoundException("Not found board with id: " + id);
        }

    }

    @Override
    public void deleteById(Long id) {
        if (boardRepository.existsById(id)){
            boardRepository.deleteById(id);
        }
        else {
            log.error("Not found board while deleting with id: " + id);
            throw new NotFoundException("Not found board while deleting with id: " + id);
        }
    }

    @Override
    public void updateBoard(Board board) {
        if (boardRepository.existsById(board.getId())){
            boardRepository.save(board);
        }
        else {
            log.error("Not found board with id: " + board.getId());
            throw new NotFoundException("Not found board with id: " + board.getId());
        }
    }
}
