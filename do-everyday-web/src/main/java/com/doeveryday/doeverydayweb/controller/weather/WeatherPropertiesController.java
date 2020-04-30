package com.doeveryday.doeverydayweb.controller.weather;

import com.doeveryday.doeverydaysecurity.model.userproperties.UserWeatherProperties;
import com.doeveryday.doeverydaysecurity.service.AppUserService;
import com.doeveryday.doeverydaysecurity.service.UserWeatherPropertiesService;
import com.doeveryday.doeverydayweb.model.BootstrapAlert;
import com.doeveryday.doeverydayweb.model.MessageToController;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Slf4j
@AllArgsConstructor
@Controller
public class WeatherPropertiesController {

    private final UserWeatherPropertiesService userWeatherPropertiesService;
    private final AppUserService appUserService;

    @GetMapping("weather/editproperties")
    @PreAuthorize("hasAnyAuthority('read:admin::weatherproperties', 'read::weatherproperties')")
    public String initEditProperties(Model model, Principal principal) throws NotFoundException {
        if (userWeatherPropertiesService.existByUsername(principal.getName())){
            model.addAttribute("properties", userWeatherPropertiesService
                    .getUserWeatherPropertiesByUsername(principal.getName()));
        }
        else {
            userWeatherPropertiesService.addUserWeatherProperties(
                    UserWeatherProperties.builder()
                    .user(appUserService.findByUsername(principal.getName()))
                    .build());
        }
        model.addAttribute("properties", userWeatherPropertiesService
                .getUserWeatherPropertiesByUsername(principal.getName()));
        model.addAttribute("message", MessageToController.builder()
                .alert(BootstrapAlert.INFO)
                .message("If you don't know you geo coordinates look at page: https://www.latlong.net/")
                .build());
        return "weather/weatherproperties";
    }

    @PostMapping("weather/editproperties")
    @PreAuthorize("hasAnyAuthority('write:admin::weatherproperties', 'write::weatherproperties')")
    public String initEditProperties(UserWeatherProperties userWeatherProperties, Principal principal) throws NotFoundException {
        userWeatherProperties.setUser(appUserService.findByUsername(principal.getName()));
        userWeatherPropertiesService.addUserWeatherProperties(userWeatherProperties);
        log.error("Im inside");
        return "redirect:";
    }
}
