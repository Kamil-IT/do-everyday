package com.doeveryday.doeverydayweb.controller.weather;

import com.doeveryday.doeverydaysecurity.service.UserWeatherPropertiesService;
import com.doeveryday.doeverydayweather.model.CurrentlyForecast;
import com.doeveryday.doeverydayweather.service.ForecastService;
import com.doeveryday.doeverydayweather.service.GeoLocationService;
import com.doeveryday.doeverydayweb.model.BootstrapAlert;
import com.doeveryday.doeverydayweb.model.MessageToController;
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
             model.addAttribute("message", MessageToController.builder()
                     .alert(BootstrapAlert.WARRING)
                     .message("If you log in you will can change location").build());
        }
        else {
            boolean userExist = userWeatherPropertiesService.existByUsername(principal.getName());
            if (userExist){
                currentWeather = forecastService.getCurrentWeather(
                        userWeatherPropertiesService
                                .getWeatherPropertiesByUsername(principal.getName()));
            }
            else {
                currentWeather = forecastService.getCurrentWeather(
                        ForecastService.DEFAULT_WEATHER_PROPERTIES_WROCLAW);
            }
        }
        model.addAttribute("current", currentWeather.getCurrently());
        model.addAttribute("location", currentWeather.getLocation());

        return "weather/current";
    }
}
