package com.doeveryday.doeverydayweb.controller.weather;

import com.doeveryday.doeverydaysecurity.service.UserWeatherPropertiesService;
import com.doeveryday.doeverydayweather.model.CurrentlyForecast;
import com.doeveryday.doeverydayweather.service.ForecastService;
import com.doeveryday.doeverydayweather.service.GeoLocationService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@AllArgsConstructor
@Controller
public class CurrentWeatherController {

    private final ForecastService forecastService;
    private final GeoLocationService geoLocationService;
    private final UserWeatherPropertiesService userWeatherPropertiesService;

    @GetMapping(value = {"weather/current", "weather"})
    public String getCurrentWeather(Model model, Principal principal) throws NotFoundException {
        CurrentlyForecast currentWeather;
        if (principal == null){
             currentWeather = forecastService.getCurrentWeather(
                    ForecastService.DEFAULT_WEATHER_PROPERTIES_WROCLAW);
        }
        else {
            currentWeather = forecastService.getCurrentWeather(
                    userWeatherPropertiesService
                            .getWeatherPropertiesByUsername(principal.getName()));
        }
        model.addAttribute("current", currentWeather.getCurrently());
        model.addAttribute("location", currentWeather.getLocation());

        return "weather/current";
    }
}
