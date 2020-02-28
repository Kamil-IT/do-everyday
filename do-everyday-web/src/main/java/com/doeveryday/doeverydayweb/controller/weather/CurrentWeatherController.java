package com.doeveryday.doeverydayweb.controller.weather;

import com.doeveryday.doeverydayweather.service.ForecastService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class CurrentWeatherController {

    private final ForecastService forecastService;

    @GetMapping(value = {"weather/current", "weather"})
    public String getCurrentWeather(Model model){
        model.addAttribute("current", forecastService.getCurrentWeather());

        return "weather/current";
    }
}
