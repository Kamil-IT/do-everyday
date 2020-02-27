package com.doeveryday.doeverydayweather.service;

import tk.plogitech.darksky.forecast.model.Currently;
import tk.plogitech.darksky.forecast.model.Daily;
import tk.plogitech.darksky.forecast.model.Hourly;

public interface ForecastRequestService {

    Currently getCurrentWeather();

    Hourly getHourlyForecast();

    Daily getDailyForecast();
}
