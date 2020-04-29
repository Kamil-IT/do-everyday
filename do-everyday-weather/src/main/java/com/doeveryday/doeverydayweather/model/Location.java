package com.doeveryday.doeverydayweather.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Location {
    private String city;
    private String country;
}
