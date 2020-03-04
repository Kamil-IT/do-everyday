package com.doeveryday.doeverydayweb.controller.todo;

import com.doeveryday.doeverydaytodo.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class ControllerExceptionsHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView modelAndView(NotFoundException expectation){

        log.error("Handling not found exceptions");
        log.error(expectation.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error404");

        modelAndView.addObject("expectation", expectation);

        return modelAndView;
    }
}
