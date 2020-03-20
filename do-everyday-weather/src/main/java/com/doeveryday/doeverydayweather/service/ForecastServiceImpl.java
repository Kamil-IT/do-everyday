package com.doeveryday.doeverydayweather.service;

import com.doeveryday.doeverydayweather.exceptions.ApiWeatherConnectionException;
import com.doeveryday.doeverydayweather.urlcreator.ForecastUrlBuild;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tk.plogitech.darksky.forecast.APIKey;
import tk.plogitech.darksky.forecast.GeoCoordinates;
import tk.plogitech.darksky.forecast.model.*;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneOffset;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.MapperFeature.AUTO_DETECT_GETTERS;
import static com.fasterxml.jackson.databind.MapperFeature.REQUIRE_SETTERS_FOR_GETTERS;
import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;

@Slf4j
@NoArgsConstructor
@Service
public class ForecastServiceImpl implements ForecastService {

    private Forecast forecast;
    private GeoCoordinates geoCoordinates;
    @Value("${default.geolocation.longitude}")
    private Double longitude;
    @Value("${default.geolocation.latitude}")
    private Double latitude;
    @Value("${darksky.api.key}")
    private String key;

    private Instant lastShotToDb;

    public Currently getCurrentWeather() {
        checkDataTopicality();
        return forecast.getCurrently();
    }

    public Hourly getHourlyForecast() {
        checkDataTopicality();
        return forecast.getHourly();
    }

    public Daily getDailyForecast() {
        checkDataTopicality();
        return forecast.getDaily();
    }

    @Override
    public GeoCoordinates getGeoLocation() {
        return this.geoCoordinates;
    }

    public void changeGeoLocation(Double longitude, Double latitude) {
        if (!this.geoCoordinates.latitude().value().equals(latitude)){
            this.latitude = latitude;
        }
        else if (!this.geoCoordinates.longitude().value().equals(longitude)){
            this.longitude = longitude;
        }
        this.geoCoordinates = new GeoCoordinates(
                new Longitude(longitude),
                new Latitude(latitude));
    }

    private void updateInformation() {
        if (this.geoCoordinates == null){
            this.geoCoordinates = new GeoCoordinates(
                    new Longitude(longitude),
                    new Latitude(latitude));
        }
        //        Building URL
        URL requestUrl = new ForecastUrlBuild()
                .language(ForecastUrlBuild.Language.en)
                .key(new APIKey(key))
                .location(geoCoordinates)
                .extendHourly()
                .build();

        ObjectMapper objectMapper = objectMapper();

//        Mapping JSON to Forecast
        try {
            forecast = objectMapper.readValue(requestUrl, Forecast.class);
            lastShotToDb = Instant.now();
        } catch (IOException e) {
            log.error("Error while getting forecast...");
            e.printStackTrace();
            throw new ApiWeatherConnectionException("Error while getting forecast...");
        }
    }

    /**
     * It setting mapper parameter for understand Forecast.class
     */
    private ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);
        objectMapper.configure(REQUIRE_SETTERS_FOR_GETTERS, false);
        objectMapper.configure(AUTO_DETECT_GETTERS, true);
        objectMapper.configure(INDENT_OUTPUT, true);
        objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);

        return objectMapper;
    }

    private void checkDataTopicality() {
        if (forecast == null) {
            updateInformation();
        } else if (!forecast.getLatitude().value().equals(this.latitude) ||
                !forecast.getLongitude().value().equals(this.longitude)) {
            updateInformation();
        } else if (lastShotToDb.atZone(ZoneOffset.UTC).getHour() != Instant.now().atZone(ZoneOffset.UTC).getHour()) {
            updateInformation();
        }

    }
}
