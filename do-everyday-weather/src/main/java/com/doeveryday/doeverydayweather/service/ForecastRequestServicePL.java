package com.doeveryday.doeverydayweather.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tk.plogitech.darksky.forecast.model.Currently;
import tk.plogitech.darksky.forecast.model.Daily;
import tk.plogitech.darksky.forecast.model.Forecast;
import tk.plogitech.darksky.forecast.model.Hourly;

@AllArgsConstructor
@Service
public class ForecastRequestServicePL implements ForecastRequestService{

    private final Forecast forecast;

    public Currently getCurrentWeather(){
        return forecast.getCurrently();
    }

    public Hourly getHourlyForecast(){
        return forecast.getHourly();
    }

    public Daily getDailyForecast(){
        return forecast.getDaily();
    }
}
