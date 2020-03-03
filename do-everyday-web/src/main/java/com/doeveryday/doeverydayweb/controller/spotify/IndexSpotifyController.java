package com.doeveryday.doeverydayweb.controller.spotify;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class IndexSpotifyController {

    @GetMapping(value = {"spotify/search/index", "spotify", "spotify/index"})
    public String initAllTypesSearch(){
        return "redirect:/spotify/search";
    }
}
