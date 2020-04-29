package com.doeveryday.doeverydayweb.controller.weather;

import com.doeveryday.doeverydaysecurity.service.UserWeatherPropertiesService;
import com.doeveryday.doeverydayweather.model.CurrentlyForecast;
import com.doeveryday.doeverydayweather.model.DailyForecast;
import com.doeveryday.doeverydayweather.model.FullForecast;
import com.doeveryday.doeverydayweather.service.ForecastService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tk.plogitech.darksky.forecast.model.Forecast;

import java.security.Principal;

@AllArgsConstructor
@Controller
public class ForecastController {

    private final ForecastService forecastService;
    private final UserWeatherPropertiesService userWeatherPropertiesService;

    @GetMapping("weather/forecast")
    public String getCurrentWeather(Model model, Principal principal) throws NotFoundException {
        FullForecast fullForecast;
        if (principal == null){
            fullForecast = forecastService.getFullForecast(
                    ForecastService.DEFAULT_WEATHER_PROPERTIES_WROCLAW);
        }
        else {
            fullForecast = forecastService.getFullForecast(
                    userWeatherPropertiesService
                            .getWeatherPropertiesByUsername(principal.getName()));
        }

        model.addAttribute("hourly", fullForecast.getForecast().getHourly());

        model.addAttribute("daily", fullForecast.getForecast().getDaily());

        return "weather/forecast";
    }
}
