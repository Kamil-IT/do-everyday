package com.doeveryday.doeverydayweb.controller.weather;

import com.byteowls.jopencage.model.JOpenCageResponse;
import com.doeveryday.doeverydayweather.service.ForecastService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class ForecastController {

    private final ForecastService forecastService;

    @GetMapping("weather/forecast")
    public String getCurrentWeather(Model model){
        model.addAttribute("hourly", forecastService.getHourlyForecast());
        model.addAttribute("daily", forecastService.getDailyForecast());

        return "weather/forecast";
    }
}
