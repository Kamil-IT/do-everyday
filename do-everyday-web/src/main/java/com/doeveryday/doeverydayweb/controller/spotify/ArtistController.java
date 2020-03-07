package com.doeveryday.doeverydayweb.controller.spotify;

import com.doeveryday.doeverydayspotify.service.SpotifySearchArtistService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@AllArgsConstructor
@Slf4j
@Controller
public class ArtistController {

    private final SpotifySearchArtistService spotifySearchArtistService;

    @GetMapping("spotify/artist/{id}/details")
    public String getArtistDetails(Model model, @PathVariable("id") String id){
        model.addAttribute("artist", spotifySearchArtistService.getArtistById(id));
        return "spotify/search/artist/artistdetails";
    }
}
