package com.doeveryday.doeverydayweather.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tk.plogitech.darksky.forecast.model.Hourly;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HourlyForecast {

    private Location location;
    private Hourly hourly;
}
