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

    private void printStackError(Exception e) {
        if (e.getMessage() != null) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @RequestMapping("/error")
    public ModelAndView handleErrorPageNotFound() {
        log.error("Page not found");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error404");
        modelAndView.addObject("exception", "Page not found");
        modelAndView.addObject("httpstatus", "404 Not Found");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);

        return modelAndView;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @RequestMapping("/error/internalservererror")
    public ModelAndView handleErrorInternalServerError(Exception e) {
        log.error("Server make some error. ");
        printStackError(e);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error404");
        if (e != null) {
            modelAndView.addObject("exception", e.getMessage());
        }
        modelAndView.addObject("httpstatus", "500 Internal Server Error");
        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @RequestMapping("/error/badrequest")
    public ModelAndView handleErrorBadRequest(Exception e) {
        log.error("Bad request error.");
        printStackError(e);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error404");
        if (e != null) {
            modelAndView.addObject("exception", e.getMessage());
        }
        modelAndView.addObject("httpstatus", "400 Bad Request");
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);

        return modelAndView;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @RequestMapping("/error/notfound")
    public ModelAndView handleErrorNotFound(Exception e) {
        log.error("Not found some stuff.");
        printStackError(e);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error404");
        if (e != null) {
            modelAndView.addObject("exception", e.getMessage());
        }
        modelAndView.addObject("httpstatus", "404 Not Found");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);

        return modelAndView;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @RequestMapping("/error/forbidden")
    public ModelAndView handleErrorForbidden() {
        log.error("Access forbidden.");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error404");

        modelAndView.addObject("exception", "You don't have permission to access");

        modelAndView.addObject("httpstatus", "403 Forbidden");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);

        return modelAndView;
    }
}
