package com.doeveryday.doeverydaysecurity.service;

import com.doeveryday.doeverydaysecurity.model.userproperties.UserWeatherProperties;
import com.doeveryday.doeverydayweather.model.WeatherProperties;
import javassist.NotFoundException;

public interface UserWeatherPropertiesService {

    WeatherProperties getWeatherPropertiesById(String id) throws NotFoundException;

    WeatherProperties getWeatherPropertiesByUserId(String id) throws NotFoundException;

    WeatherProperties getWeatherPropertiesByUsername(String username) throws NotFoundException;

    UserWeatherProperties getUserWeatherPropertiesByUsername(String username) throws NotFoundException;

    UserWeatherProperties addUserWeatherProperties(UserWeatherProperties userWeatherProperties);

    UserWeatherProperties updateUserWeatherProperties(UserWeatherProperties userWeatherProperties);

    void removeUserWeatherPropertiesById(String Id);

    boolean existByUsername(String username);
}
