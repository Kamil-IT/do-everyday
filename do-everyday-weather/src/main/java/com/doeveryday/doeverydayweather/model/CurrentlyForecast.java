package com.doeveryday.doeverydayweather.model;

import lombok.*;
import tk.plogitech.darksky.forecast.model.Currently;
import tk.plogitech.darksky.forecast.model.Daily;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrentlyForecast {

    private Location location;
    private Currently currently;
}
