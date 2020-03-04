package com.doeveryday.doeverydayweather.service;

import com.byteowls.jopencage.model.JOpenCageResult;

import java.util.List;

public interface GeoLocationService {

    List<JOpenCageResult> getFullResponse();

    JOpenCageResult getFirstResult();
}
