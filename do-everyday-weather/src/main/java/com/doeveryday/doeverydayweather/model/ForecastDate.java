package com.doeveryday.doeverydayweather.model;

import lombok.Getter;
import lombok.Setter;
import tk.plogitech.darksky.forecast.model.Forecast;

import java.time.Instant;

@Getter
@Setter
public class ForecastDate extends Forecast {
    /**
     * This time does not include in equal and hash code
     */
    Instant timeGetThisWeather = Instant.now();

    public ForecastDate(Forecast forecast){
        this.setAlerts(forecast.getAlerts());
        this.setCurrently(forecast.getCurrently());
        this.setDaily(forecast.getDaily());
        this.setFlags(forecast.getFlags());
        this.setHourly(forecast.getHourly());
        this.setLatitude(forecast.getLatitude());
        this.setLongitude(forecast.getLongitude());
        this.setMinutely(forecast.getMinutely());
        this.setTimezone(forecast.getTimezone());
    }
}
