package com.doeveryday.doeverydayweather.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.plogitech.darksky.forecast.model.Forecast;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullForecast {

    Forecast forecast;
    Location location;
}
