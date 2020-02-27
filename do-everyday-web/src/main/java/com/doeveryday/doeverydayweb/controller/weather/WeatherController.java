package com.doeveryday.doeverydayweb.controller.weather;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherController {

    @GetMapping("weather")
    public String mainPage(){
        return "weather/index";
    }
}
