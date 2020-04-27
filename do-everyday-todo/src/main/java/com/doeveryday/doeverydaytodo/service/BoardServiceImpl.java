package com.doeveryday.doeverydaytodo.service;

import com.doeveryday.doeverydaysecurity.model.AppUser;
import com.doeveryday.doeverydaysecurity.repository.AppUserRepository;
import com.doeveryday.doeverydaytodo.exceptions.NotFoundException;
import com.doeveryday.doeverydaytodo.models.Board;
import com.doeveryday.doeverydaytodo.models.TaskManager;
import com.doeveryday.doeverydaytodo.repository.BoardRepository;
import com.doeveryday.doeverydaytodo.repository.TaskMemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final TaskMemberRepository taskMemberRepository;
    private final AppUserRepository appUserRepository;

    @Override
    public Board saveBord(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public List<Board> getBoards() {
        List<Board> boards = new ArrayList<>();
        boardRepository.findAll().forEach(boards::add);
        return boards.stream().filter(board -> board.getAppUsers() == null).collect(Collectors.toList());
    }

    @Override
    public Board findById(Long id) {
        return boardRepository.findById(id).orElseThrow(() ->
        new NotFoundException("Not found board with id: " + id));
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

    @Override
    public boolean existsById(Long id) {
        return boardRepository.existsById(id);
    }

    @Override
    public List<Board> getBoards(String user_id) {
        List<TaskManager> taskManagers = new ArrayList<>();
        taskMemberRepository.findAllByAppUsers_Id(user_id)
                .forEach(taskMember ->
                        taskManagers.addAll(
                                taskMember.getTaskManagers()));

        List<Board> tem = new ArrayList<>();
        boardRepository.findAll().forEach(tem::add);
        List<Board> boardsWithOutTasks = tem
                .stream()
                .filter(board -> {
                    if (board.getAppUsers() != null){
                        return board.getAppUsers().getId().equals(user_id);
                    }
                    return false;
                })
                .collect(Collectors.toList());

        List<Board> boardsWithTasks = taskManagers.stream()
                .map(taskManager ->
                        taskManager
                                .getTask()
                                .getBoard())
                .distinct()
                .collect(Collectors.toList());
        List<Board> boards = new ArrayList<>();
        boards.addAll(boardsWithOutTasks);
        boards.addAll(boardsWithTasks);
        return boards.stream().distinct().collect(Collectors.toList());
    }


    @Override
    public Board saveBord(Board board, String user_id) {
        Optional<AppUser> userOptional = appUserRepository.findById(user_id);
        if (userOptional.isEmpty()){
            throw new NotFoundException("Not found user with id: " + user_id);
        }
        board.setAppUsers(appUserRepository.findById(user_id).orElseThrow());

        return boardRepository.save(board);
    }
}
