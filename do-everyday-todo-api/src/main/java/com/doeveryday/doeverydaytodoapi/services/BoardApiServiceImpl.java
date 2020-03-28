package com.doeveryday.doeverydaytodoapi.services;

import com.doeveryday.doeverydaytodo.models.Board;
import com.doeveryday.doeverydaytodo.models.Task;
import com.doeveryday.doeverydaytodo.repository.BoardRepository;
import com.doeveryday.doeverydaytodo.repository.TaskRepository;
import com.doeveryday.doeverydaytodoapi.api.v1.mapper.BoardMapper;
import com.doeveryday.doeverydaytodoapi.api.v1.mapper.TaskMapper;
import com.doeveryday.doeverydaytodoapi.api.v1.model.BoardDTO;
import com.doeveryday.doeverydaytodoapi.api.v1.model.TaskDTO;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BoardApiServiceImpl implements BoardApiService {

    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;
    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;

    @Override
    public List<BoardDTO> getAllBoards() {
        List<BoardDTO> boardDTOs = new ArrayList<>();
        boardRepository.findAll().forEach(board -> boardDTOs.add(boardMapper.boardToBoardDTO(board)));
        return boardDTOs;
    }

    @Override
    public BoardDTO getBoardById(Long id) throws NotFoundException {
        return boardMapper.boardToBoardDTO(
                boardRepository
                        .findById(id)
                        .orElseThrow(() -> new NotFoundException("Not found board with id: " + id)));
    }

    @Override
    public BoardDTO createBoard(BoardDTO boardDTO) {
        boardDTO.setId(null);
        Board boardToSave = boardMapper.boardDTOToBoard(boardDTO);

        addTasksToBoard(boardDTO, boardToSave);

        return boardMapper.boardToBoardDTO(boardRepository.save(boardToSave));
    }

    @Override
    public void deleteBoardById(Long id) throws NotFoundException {
        if (boardRepository.existsById(id)){
            boardRepository.deleteById(id);
        }
        else {
            throw new NotFoundException("Not found board with id: " + id);
        }
    }

    @Override
    public BoardDTO putBoard(BoardDTO boardDTO, Long id) throws NotFoundException {
        if (id == null){
            throw new NullPointerException("Id cannot be null");
        }
        else if (!boardRepository.existsById(boardDTO.getId())){
            throw new NotFoundException("Not found board with id: " + boardDTO.getId());
        }

        Board boardToSave = boardMapper.boardDTOToBoard(boardDTO);
        boardToSave.setId(id);
        addTasksToBoard(boardDTO, boardToSave);
        return boardMapper.boardToBoardDTO(boardRepository.save(boardToSave));
    }

    private void addTasksToBoard(BoardDTO boardDTO, Board boardToSave) {
        List<Task> tasks = new ArrayList<>();
        for (TaskDTO taskDTO :
                boardDTO.getTasks()) {
            taskRepository.findById(taskDTO.getId())
                    .ifPresentOrElse(
                            tasks::add,
                            () -> tasks.add(taskMapper.taskDTOToTask(taskDTO)));
        }
        boardToSave.setTasks(tasks);
    }
}
