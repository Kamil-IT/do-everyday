package com.doeveryday.doeverydayweather.service;

import com.doeveryday.doeverydayweather.model.*;
import com.doeveryday.doeverydayweather.urlcreator.ForecastUrlBuild;
import tk.plogitech.darksky.forecast.model.Currently;
import tk.plogitech.darksky.forecast.model.Daily;
import tk.plogitech.darksky.forecast.model.Hourly;

public interface ForecastService {

    /**
     * Default properties for current weather in Wrocław
     * City: Wrocłąw, Latitude: 51.107883, Longitude: 17.038538
     * Units: Si(International System of Units)
     * Language: EN
     */
    WeatherProperties DEFAULT_WEATHER_PROPERTIES_WROCLAW = WeatherProperties.builder()
            .language(ForecastUrlBuild.Language.en)
            .latitude(51.107883)
            .longitude(17.038538)
            .units(ForecastUrlBuild.Units.si)
            .build();

    CurrentlyForecast getCurrentWeather(WeatherProperties properties);

    HourlyForecast getHourlyForecast(WeatherProperties properties);

    DailyForecast getDailyForecast(WeatherProperties properties);

    FullForecast getFullForecast(WeatherProperties properties);
}
