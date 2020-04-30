package com.doeveryday.doeverydaysecurity.service;

import com.doeveryday.doeverydaysecurity.model.userproperties.UserWeatherProperties;
import com.doeveryday.doeverydaysecurity.repository.AppUserRepository;
import com.doeveryday.doeverydaysecurity.repository.UserWeatherPropertiesRepository;
import com.doeveryday.doeverydayweather.model.WeatherProperties;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserWeatherPropertiesServiceImpl implements UserWeatherPropertiesService {

    private final AppUserRepository appUserRepository;
    private final UserWeatherPropertiesRepository userWeatherPropertiesRepository;

    public UserWeatherPropertiesServiceImpl(AppUserRepository appUserRepository, UserWeatherPropertiesRepository userWeatherPropertiesRepository) {
        this.appUserRepository = appUserRepository;
        this.userWeatherPropertiesRepository = userWeatherPropertiesRepository;
    }

    @Override
    public WeatherProperties getWeatherPropertiesById(String id) throws NotFoundException {
        return userWeatherPropertiesRepository
                .findById(id).orElseThrow(() ->
                        new NotFoundException("Not found Weather properties with id: " + id));
    }

    @Override
    public WeatherProperties getWeatherPropertiesByUserId(String id) throws NotFoundException {
        return userWeatherPropertiesRepository.findFirstByUser_Id(id).orElseThrow(() ->
                new NotFoundException("Not found Weather properties with user id: " + id));
    }

    @Override
    public WeatherProperties getWeatherPropertiesByUsername(String username) throws NotFoundException {
        return userWeatherPropertiesRepository.findFirstByUser_Username(username).orElseThrow(() ->
                new NotFoundException("Not found Weather properties with user username: " + username));
    }

    @Override
    public UserWeatherProperties getUserWeatherPropertiesByUsername(String username) throws NotFoundException {
        return userWeatherPropertiesRepository.findFirstByUser_Username(username).orElseThrow(() ->
                new NotFoundException("Not found Weather properties with user username: " + username));
    }

    @Override
    public UserWeatherProperties addUserWeatherProperties(UserWeatherProperties userWeatherProperties) {
        return userWeatherPropertiesRepository.save(userWeatherProperties);
    }

    @Override
    public UserWeatherProperties updateUserWeatherProperties(UserWeatherProperties userWeatherProperties) {
        if (!userWeatherPropertiesRepository.existsById(userWeatherProperties.getId())){
            throw new IllegalArgumentException("Not found user with id: " + userWeatherProperties.getId());
        }
        return userWeatherPropertiesRepository.save(userWeatherProperties);
    }

    @Override
    public void removeUserWeatherPropertiesById(String id) {
        if (!userWeatherPropertiesRepository.existsById(id)){
            throw new IllegalArgumentException("Not found user with id: " + id);
        }
        userWeatherPropertiesRepository.deleteById(id);
    }

    @Override
    public boolean existByUsername(String username) {
        return userWeatherPropertiesRepository.findFirstByUser_Username(username).isPresent();
    }
}
