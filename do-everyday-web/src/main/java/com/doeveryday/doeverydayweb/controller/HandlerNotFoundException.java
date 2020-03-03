package com.doeveryday.doeverydayweb.controller;

import com.wrapper.spotify.exceptions.detailed.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class HandlerNotFoundException {


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(NotFoundException expectation){

        log.error("Handling not found exceptions");
        log.error(expectation.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");

        modelAndView.addObject("expectation", expectation);


        return modelAndView;
    }
}
