package com.doeveryday.doeverydayweb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class HandlerWhitelabelErrorPage implements ErrorController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @RequestMapping("/error")
    public ModelAndView handleError() {
        log.error("Page not found...");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error404");

        return modelAndView;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
