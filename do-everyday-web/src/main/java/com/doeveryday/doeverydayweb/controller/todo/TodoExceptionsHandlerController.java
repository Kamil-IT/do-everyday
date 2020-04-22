package com.doeveryday.doeverydayweb.controller.todo;

import com.doeveryday.doeverydaytodo.exceptions.NotFoundException;
import com.doeveryday.doeverydayweb.controller.ErrorPagesController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@AllArgsConstructor
@ControllerAdvice
public class TodoExceptionsHandlerController {

    private final ErrorPagesController errorPagesController;

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(NotFoundException expectation){
        return errorPagesController.handleErrorNotFound(expectation);
    }
}
