package com.doeveryday.doeverydayweb.controller.spotify;

import com.doeveryday.doeverydayspotify.model.SpotifyAllTypes;
import com.doeveryday.doeverydayspotify.service.SpotifySearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class SpotifySearcherController {

    private SpotifySearchService spotifySearchService;
    private SpotifyAllTypes searchResult;

    public SpotifySearcherController(SpotifySearchService spotifySearchService) {
        this.spotifySearchService = spotifySearchService;
    }

    @GetMapping("spotify/search")
    public String initAllTypesSearch(Model model,
                                     @RequestParam(name = "name", required = false) String name,
                                     @RequestParam(name = "limit", required = false) Integer limit) {

        if (name != null && limit != null) {
            if (name.length() == 0) {
                return "spotify/search/searchform";
            }
            searchResult = spotifySearchService.spotifyAllTypesByName(name, limit);
        } else if (name != null) {
            if (name.length() == 0) {
                return "spotify/search/searchform";
            }
            searchResult = spotifySearchService.spotifyAllTypesByName(name);
        } else {
            return "spotify/search/searchform";
        }

        model.addAttribute("artists", searchResult.getArtistsList());
        model.addAttribute("albums", searchResult.getAlbumSimplifiedsList());
        model.addAttribute("tracks", searchResult.getTracks());

        return "spotify/search/searchform";
    }
}
