package com.doeveryday.doeverydayweb.controller.weather;

import com.doeveryday.doeverydayweather.exceptions.NotFoundException;
import com.doeveryday.doeverydayweather.exceptions.NullPointerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class WeatherControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handlerNotFoundException(NotFoundException exception){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error404");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullPointerException.class)
    public ModelAndView handlerNullPointerException(NullPointerException exception){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error404");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }
}
