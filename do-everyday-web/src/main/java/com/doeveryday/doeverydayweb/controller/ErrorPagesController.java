package com.doeveryday.doeverydayweb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class ErrorPagesController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @RequestMapping("/error")
    public ModelAndView handleErrorPageNotFound() {
        log.error("Page not found");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error404");
        modelAndView.addObject("exception", "Page not found");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);

        return modelAndView;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @RequestMapping("/error/internalservererror")
    public ModelAndView handleErrorInternalServerError(Exception e) {
        log.error("Server make some error. " + e.getMessage());
        e.printStackTrace();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error404");
        modelAndView.addObject("exception", e.getMessage());
        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @RequestMapping("/error/badrequest")
    public ModelAndView handleErrorBadRequest(Exception e) {
        log.error("Bad request error. " + e.getMessage());
        e.printStackTrace();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error404");
        modelAndView.addObject("exception", e.getMessage());
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);

        return modelAndView;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @RequestMapping("/error/notfound")
    public ModelAndView handleErrorNotFound(Exception e) {
        log.error("Not found some stuff. " + e.getMessage());
        e.printStackTrace();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error404");
        modelAndView.addObject("exception", e.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);

        return modelAndView;
    }
}
