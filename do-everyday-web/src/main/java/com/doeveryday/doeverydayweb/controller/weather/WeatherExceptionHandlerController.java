package com.doeveryday.doeverydayweb.controller.weather;

import com.doeveryday.doeverydayweather.exceptions.NotFoundException;
import com.doeveryday.doeverydayweather.exceptions.NullPointerException;
import com.doeveryday.doeverydayweb.controller.ErrorPagesController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@AllArgsConstructor
@ControllerAdvice
public class WeatherExceptionHandlerController {

    private final ErrorPagesController errorPagesController;

    @ExceptionHandler(NotFoundException.class)
    public void handlerNotFoundException(NotFoundException exception){
        errorPagesController.handleErrorNotFound(exception);
    }

    @ExceptionHandler(NullPointerException.class)
    public ModelAndView handlerNullPointerException(NullPointerException exception){
        return errorPagesController.handleErrorBadRequest(exception);
    }
}