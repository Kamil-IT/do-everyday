package com.doeveryday.doeverydaytodoapi.services;

import com.doeveryday.doeverydaytodo.exceptions.NotFoundException;
import com.doeveryday.doeverydaytodo.models.Board;
import com.doeveryday.doeverydaytodo.repository.BoardRepository;
import com.doeveryday.doeverydaytodoapi.api.v1.mapper.BoardMapper;
import com.doeveryday.doeverydaytodoapi.api.v1.model.BoardDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BoardApiServiceImpl implements BoardApiService {

    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    @Override
    public List<BoardDTO> getAllBoards() {
        List<BoardDTO> boardDTOs = new ArrayList<>();
        boardRepository.findAll().forEach(board -> {
            boardDTOs.add(boardMapper.boardToBoardDTO(board));
        });
        return boardDTOs;
    }

    @Override
    public BoardDTO getBoardById(Long id) {
        return boardMapper.boardToBoardDTO(
                boardRepository
                        .findById(id)
                        .orElseThrow(() -> new NotFoundException("Not found board with id: " + id)));
    }

    @Override
    public BoardDTO createBoard(BoardDTO boardDTO) {
        boardDTO.setId(null);
        Board savedBoard = boardRepository.save(boardMapper.boardDTOToBoard(boardDTO));
        return boardMapper.boardToBoardDTO(savedBoard);
    }

    @Override
    public void deleteBoardById(Long id) {
        if (boardRepository.existsById(id)){
            boardRepository.deleteById(id);
        }
        else {
            throw new NotFoundException("Not found board with id: " + id);
        }
    }

    @Override
    public BoardDTO putBoard(BoardDTO boardDTO, Long id) {
        if (id == null){
            throw new NullPointerException("Id cannot be null");
        }
        else if (!boardRepository.existsById(boardDTO.getId())){
            throw new NotFoundException("Not found board with id: " + boardDTO.getId());
        }

        Board boardToSave = boardMapper.boardDTOToBoard(boardDTO);
        boardToSave.setId(id);
        return boardMapper.boardToBoardDTO(boardRepository.save(boardToSave));
    }
}
