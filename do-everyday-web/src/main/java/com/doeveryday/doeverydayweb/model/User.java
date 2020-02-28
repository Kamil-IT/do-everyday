package com.doeveryday.doeverydayweb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import tk.plogitech.darksky.forecast.GeoCoordinates;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    public Language language;
    public GeoCoordinates geoCoordinates;
}
