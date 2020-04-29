package com.doeveryday.doeverydayweather.model;

import lombok.*;
import tk.plogitech.darksky.forecast.model.Daily;
import tk.plogitech.darksky.forecast.model.Forecast;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyForecast {

    private Location location;
    private Daily daily;
}
