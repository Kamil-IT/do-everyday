package com.doeveryday.doeverydayweather.config;


import com.doeveryday.doeverydayweather.urlcreator.ForecastUrlBuild;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.plogitech.darksky.forecast.APIKey;
import tk.plogitech.darksky.forecast.GeoCoordinates;
import tk.plogitech.darksky.forecast.model.Forecast;
import tk.plogitech.darksky.forecast.model.Latitude;
import tk.plogitech.darksky.forecast.model.Longitude;

import java.io.IOException;
import java.net.URL;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.MapperFeature.AUTO_DETECT_GETTERS;
import static com.fasterxml.jackson.databind.MapperFeature.REQUIRE_SETTERS_FOR_GETTERS;
import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;

@Slf4j
@Configuration
public class ForecastConfiguration {

    @Value("${darksky.api.key}")
    private String key;

    @Bean
    Forecast forecast(){
        Longitude longitude = new Longitude(17.038538);
        Latitude latitude = new Latitude(51.107883);

        URL requestUrl = new ForecastUrlBuild()
                .language(ForecastUrlBuild.Language.en)
                .key(new APIKey(key))
                .location(new GeoCoordinates(longitude, latitude)).extendHourly()
                .build();

        ObjectMapper objectMapper = objectMapper();

        try {
            return objectMapper.readValue(requestUrl, Forecast.class);
        } catch (IOException e) {
            log.error("Error while getting forecast...");
            e.printStackTrace();
        }

        return null;
    }

    ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);
        objectMapper.configure(REQUIRE_SETTERS_FOR_GETTERS, false);
        objectMapper.configure(AUTO_DETECT_GETTERS, true);
        objectMapper.configure(INDENT_OUTPUT, true);
        objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);

        return objectMapper;
    }
}
