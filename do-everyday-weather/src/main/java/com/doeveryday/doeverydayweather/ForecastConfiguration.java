package com.doeveryday.doeverydayweather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.plogitech.darksky.api.jackson.DarkSkyJacksonClient;
import tk.plogitech.darksky.forecast.*;
import tk.plogitech.darksky.forecast.model.Forecast;
import tk.plogitech.darksky.forecast.model.Latitude;
import tk.plogitech.darksky.forecast.model.Longitude;

@Configuration
public class ForecastConfiguration {

    @Value("${darksky.api.key}")
    private String key;

    @Bean
    Forecast forecast(){
        Longitude longitude = new Longitude(51.107883);
        Latitude latitude = new Latitude(17.038538);

        ForecastRequest forecastRequest = new ForecastRequestBuilder()
                .language(ForecastRequestBuilder.Language.pl)
                .key(new APIKey(key))
                .location(new GeoCoordinates(longitude, latitude))
                .build();

        DarkSkyJacksonClient client = new DarkSkyJacksonClient();

        try {
            return client.forecast(forecastRequest);
        } catch (ForecastException e) {
            e.printStackTrace();
        }
        return null;
    }
}
