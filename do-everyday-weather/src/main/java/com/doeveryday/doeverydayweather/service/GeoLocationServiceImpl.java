package com.doeveryday.doeverydayweather.service;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageComponents;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.byteowls.jopencage.model.JOpenCageResult;
import com.byteowls.jopencage.model.JOpenCageReverseRequest;
import com.doeveryday.doeverydayweather.exceptions.NotFoundException;
import com.doeveryday.doeverydayweather.model.WeatherProperties;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@NoArgsConstructor
@Service
public class GeoLocationServiceImpl implements GeoLocationService {

    @Value("${opencage.api.key}")
    private String API_KEY;

    private final Map<JOpenCageProperties, JOpenCageResponse> propertiesResponseMap = new HashMap<>();

    @Override
    public List<JOpenCageResult> getFullResponse(WeatherProperties properties) {
        return getDataFromApi(properties.getLongitude(), properties.getLatitude(), properties.getLanguage().name()).getResults();
    }

    @Override
    public JOpenCageComponents getFirstResult(WeatherProperties properties) {
        JOpenCageResponse dataFromApi = getDataFromApi(properties.getLongitude(), properties.getLatitude(), properties.getLanguage().name());
        List<JOpenCageResult> results = dataFromApi.getResults();
        if (results.isEmpty()) {
            throw new NotFoundException("Not found location: " + dataFromApi.getStatus());
        }
        return results.get(0).getComponents();
    }

    @Override
    public List<JOpenCageResult> getFullResponse(Double longitude, Double latitude, String language) {
        return getDataFromApi(longitude, latitude, language).getResults();
    }

    @Override
    public JOpenCageComponents getFirstResult(Double longitude, Double latitude, String language) {
        JOpenCageResponse dataFromApi = getDataFromApi(longitude, latitude, language);
        List<JOpenCageResult> results = dataFromApi.getResults();
        if (results.isEmpty()) {
            throw new NotFoundException("Not found location: " + dataFromApi.getStatus());
        }
        return results.get(0).getComponents();
    }

    private JOpenCageResponse getDataFromApi(Double longitude, Double latitude, String language){
        JOpenCageProperties properties = new JOpenCageProperties(longitude, latitude, language);
        if (propertiesResponseMap.containsKey(properties)){
            return propertiesResponseMap.get(properties);
        }

        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder(API_KEY);

        JOpenCageReverseRequest request = new JOpenCageReverseRequest(latitude, longitude);
        request.setNoAnnotations(true);
        request.setLanguage(language);

        JOpenCageResponse response = jOpenCageGeocoder.reverse(request);
        propertiesResponseMap.put(properties, response);
        return response;
    }

    @Getter
    @Setter
    @EqualsAndHashCode
    @AllArgsConstructor
    class JOpenCageProperties{
        private Double longitude;
        private Double latitude;
        private String language;
    }
}
