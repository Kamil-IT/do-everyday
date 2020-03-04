package com.doeveryday.doeverydayweb.controller.weather;

import com.doeveryday.doeverydayweather.service.ForecastService;
import com.doeveryday.doeverydayweather.service.GeoLocationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class CurrentWeatherController {

    private final ForecastService forecastService;
    private final GeoLocationService geoLocationService;

    @GetMapping(value = {"weather/current", "weather"})
    public String getCurrentWeather(Model model){
        model.addAttribute("current", forecastService.getCurrentWeather());
        model.addAttribute("location", geoLocationService.getFirstResult());

        return "weather/current";
    }
}
