package com.doeveryday.doeverydayweb.controller.weather;

import com.doeveryday.doeverydaysecurity.model.userproperties.UserWeatherProperties;
import com.doeveryday.doeverydaysecurity.service.UserWeatherPropertiesService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@AllArgsConstructor
@Controller
public class WeatherPropertiesController {

    private final UserWeatherPropertiesService userWeatherPropertiesService;

    @GetMapping("weather/editproperties")
    @PreAuthorize("hasAnyAuthority('read:admin::weatherproperties', 'read::weatherproperties')")
    public String initEditProperties(Model model, Principal principal) throws NotFoundException {
        model.addAttribute("properties", userWeatherPropertiesService
                .getUserWeatherPropertiesByUsername(principal.getName()));
        return "weather/weatherproperties";
    }

    @PostMapping("weather/editproperties")
    @PreAuthorize("hasAnyAuthority('write:admin::weatherproperties', 'write::weatherproperties')")
    public String initEditProperties(UserWeatherProperties userWeatherProperties) {
        userWeatherPropertiesService.addUserWeatherProperties(userWeatherProperties);
        return "redirect:weather/editproperties";
    }
}
