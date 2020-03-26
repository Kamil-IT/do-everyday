package com.doeveryday.doeverydayweather.service;

import com.byteowls.jopencage.model.JOpenCageComponents;
import com.byteowls.jopencage.model.JOpenCageResult;

import java.util.List;

public interface GeoLocationService {

    List<JOpenCageResult> getFullResponse();

    JOpenCageComponents getFirstResult();

    void changeLanguage(String language);

    void changeGeoLocation(double latitude, double longitude);
}
