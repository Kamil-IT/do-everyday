package com.doeveryday.doeverydayweb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class HandlerWhitelabelErrorPage implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error/notfoundpage";
    }
}
