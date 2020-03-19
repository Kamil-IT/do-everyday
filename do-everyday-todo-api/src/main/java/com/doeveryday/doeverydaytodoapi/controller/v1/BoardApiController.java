package com.doeveryday.doeverydaytodoapi.controller.v1;

import com.doeveryday.doeverydaytodoapi.api.v1.model.BoardDTO;
import com.doeveryday.doeverydaytodoapi.api.v1.model.ListBoardDTO;
import com.doeveryday.doeverydaytodoapi.services.BoardApiService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(BoardApiController.BASE_URL)
public class BoardApiController {

    public static final String BASE_URL = "/api/v1/board";

    private final BoardApiService boardApiService;

    public BoardApiController(BoardApiService boardApiService) {
        this.boardApiService = boardApiService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ListBoardDTO getAllBoards(){
        return new ListBoardDTO(boardApiService.getAllBoards());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BoardDTO getBoardById(@PathVariable("id") Long id){
        return boardApiService.getBoardById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BoardDTO postBoardById(@RequestBody BoardDTO boardDTO){
        return boardApiService.createBoard(boardDTO);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public BoardDTO putBoard(@RequestBody BoardDTO boardDTO){
        return boardApiService.putBoard(boardDTO, boardDTO.getId());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BoardDTO putBoard(@RequestBody BoardDTO boardDTO, @PathVariable("id") Long id){
        boardDTO.setId(id);
        return boardApiService.putBoard(boardDTO, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteBoard(@PathVariable("id") Long id){
        boardApiService.deleteBoardById(id);
        return "Delete board with id = " + id + " was successful";
    }

}