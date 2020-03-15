package com.doeveryday.doeverydayweather.config;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.byteowls.jopencage.model.JOpenCageReverseRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.plogitech.darksky.forecast.model.Latitude;
import tk.plogitech.darksky.forecast.model.Longitude;

@Slf4j
@Configuration
public class GeoLocationConfiguration {

    @Value("${opencage.api.key}")
    private String API_KEY;

    @Bean
    JOpenCageResponse jOpenCageResponse(){
//        Setting geo location
        Longitude longitude = new Longitude(17.038538);
        Latitude latitude = new Latitude(51.107883);
//        Setting language
        String language = "en";

        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder(API_KEY);

        JOpenCageReverseRequest request = new JOpenCageReverseRequest(latitude.value(), longitude.value());
        request.setNoAnnotations(true);
        request.setLanguage(language);

        return jOpenCageGeocoder.reverse(request);
    }
}
