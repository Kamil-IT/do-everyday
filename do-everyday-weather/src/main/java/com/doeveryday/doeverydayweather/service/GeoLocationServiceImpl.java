package com.doeveryday.doeverydayweather.service;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageComponents;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.byteowls.jopencage.model.JOpenCageResult;
import com.byteowls.jopencage.model.JOpenCageReverseRequest;
import com.doeveryday.doeverydayweather.exceptions.NotFoundException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tk.plogitech.darksky.forecast.model.Latitude;
import tk.plogitech.darksky.forecast.model.Longitude;

import java.util.List;

@Slf4j
@NoArgsConstructor
@Service
public class GeoLocationServiceImpl implements GeoLocationService {

    private JOpenCageResponse jOpenCageResponse;
    private String language = "en";
    @Value("${default.geolocation.latitude}")
    private Double latitude = 51.107883;
    @Value("${default.geolocation.longitude}")
    private Double longitude;
    @Value("${opencage.api.key}")
    private String API_KEY;

    @Override
    public List<JOpenCageResult> getFullResponse() {
        if (jOpenCageResponse == null){
            getDataFromApi();
        }
        return jOpenCageResponse.getResults();
    }

    @Override
    public JOpenCageComponents getFirstResult() {
        if (jOpenCageResponse == null){
            getDataFromApi();
        }
        List<JOpenCageResult> results = jOpenCageResponse.getResults();
        if (results.isEmpty()) {
            throw new NotFoundException("Not found location: " + jOpenCageResponse.getStatus());
        }
        return results.get(0).getComponents();
    }

    @Override
    public void changeLanguage(String language) {
        this.language = language;
        getDataFromApi();
    }

    @Override
    public void changeGeoLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        getDataFromApi();
    }

    private void getDataFromApi(){
        Longitude longitude = new Longitude(this.longitude);
        Latitude latitude = new Latitude(this.latitude);

//        Setting default language
        String language = this.language;

        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder(API_KEY);

        JOpenCageReverseRequest request = new JOpenCageReverseRequest(latitude.value(), longitude.value());
        request.setNoAnnotations(true);
        request.setLanguage(language);

        this.jOpenCageResponse = jOpenCageGeocoder.reverse(request);
    }
}
