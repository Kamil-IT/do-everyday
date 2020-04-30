package com.doeveryday.doeverydayweather.model;

import com.doeveryday.doeverydayweather.urlcreator.ForecastUrlBuild;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class WeatherProperties {

    private Double longitude;
    private Double latitude;
    private ForecastUrlBuild.Language language;
    private ForecastUrlBuild.Units units;
}
