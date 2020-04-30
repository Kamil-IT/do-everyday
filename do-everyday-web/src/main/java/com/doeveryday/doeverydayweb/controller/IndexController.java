package com.doeveryday.doeverydayweb.controller;

import com.doeveryday.doeverydaybudgetmanager.service.BudgetService;
import com.doeveryday.doeverydaybudgetmanager.service.TransactionService;
import com.doeveryday.doeverydaysecurity.service.UserWeatherPropertiesService;
import com.doeveryday.doeverydaytodo.service.BoardService;
import com.doeveryday.doeverydayweather.model.CurrentlyForecast;
import com.doeveryday.doeverydayweather.service.ForecastService;
import com.doeveryday.doeverydayweather.service.GeoLocationService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Slf4j
@AllArgsConstructor
@Controller
public class IndexController {

    private final ForecastService forecastService;
    private final GeoLocationService geoLocationService;
    private final BoardService boardService;
    private final BudgetService budgetService;
    private final TransactionService transactionService;
    private final UserWeatherPropertiesService userWeatherPropertiesService;


    @GetMapping(value = {"/", "/index"})
    public String getIndex(Model model, Principal principal) throws NotFoundException {
//        Weather
        CurrentlyForecast currentWeather;
        if (principal == null){
            currentWeather = forecastService.getCurrentWeather(
                    ForecastService.DEFAULT_WEATHER_PROPERTIES_WROCLAW);
        }
        else {
            if (userWeatherPropertiesService.existByUsername(principal.getName())){
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

//        Board
        model.addAttribute("boards", boardService.getBoards());

//        Budget
        model.addAttribute("transactions", transactionService.getTransactionCreateToday());

        return "/index";
    }
}
