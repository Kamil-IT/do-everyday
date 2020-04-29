package com.doeveryday.doeverydayweather.service;

import com.byteowls.jopencage.model.JOpenCageComponents;
import com.byteowls.jopencage.model.JOpenCageResult;
import com.doeveryday.doeverydayweather.model.WeatherProperties;

import java.util.List;
import java.util.Properties;

public interface GeoLocationService {

    List<JOpenCageResult> getFullResponse(WeatherProperties properties);


    JOpenCageComponents getFirstResult(WeatherProperties properties);

    /**
     * An IETF format language code (such as es for Spanish or pt-BR for Brazilian Portuguese); if this is omitted a code of en (English) will be assumed
     * @param language the language code
     */
    List<JOpenCageResult> getFullResponse(Double longitude, Double latitude, String language);

    /**
     * An IETF format language code (such as es for Spanish or pt-BR for Brazilian Portuguese); if this is omitted a code of en (English) will be assumed
     * @param language the language code
     */
    JOpenCageComponents getFirstResult(Double longitude, Double latitude, String language);
}
