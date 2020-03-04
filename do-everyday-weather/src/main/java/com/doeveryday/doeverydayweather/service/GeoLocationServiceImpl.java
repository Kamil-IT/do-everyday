package com.doeveryday.doeverydayweather.service;

import com.byteowls.jopencage.model.JOpenCageComponents;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.byteowls.jopencage.model.JOpenCageResult;
import com.doeveryday.doeverydayweather.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class GeoLocationServiceImpl implements GeoLocationService {

    private final JOpenCageResponse jOpenCageResponse;

    @Override
    public List<JOpenCageResult> getFullResponse() {
        return jOpenCageResponse.getResults();
    }

    @Override
    public JOpenCageComponents getFirstResult() {
        List<JOpenCageResult> results = jOpenCageResponse.getResults();
        if (results.isEmpty()){
            throw new NotFoundException("Not found location: " + jOpenCageResponse.getStatus());
        }
        return results.get(0).getComponents();
    }
}
