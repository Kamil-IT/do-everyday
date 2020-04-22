package com.doeveryday.doeverydayweb.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor
@ControllerAdvice
public class HandlerCommonExceptions {

    private final ErrorPagesController errorPagesController;

    @ExceptionHandler(NullPointerException.class)
    public ModelAndView NullPointerExceptionController(NullPointerException exception){
        return errorPagesController.handleErrorBadRequest(exception);
    }
}
