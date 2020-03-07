package com.doeveryday.doeverydayweb.controller.spotify;

import com.doeveryday.doeverydayspotify.service.SpotifySearchTrackService;
import com.wrapper.spotify.model_objects.specification.Track;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;

@Slf4j
@AllArgsConstructor
@Controller
public class TrackController {

    private final SpotifySearchTrackService spotifySearchTrackService;

    @GetMapping("spotify/track/{id}/details")
    public String getTrackDetails(Model model, @PathVariable("id") String id){
        Track track = spotifySearchTrackService.getTrackById(id);
        model.addAttribute("track", track);
        model.addAttribute("artists", Arrays.asList(track.getArtists()));
        return "spotify/search/track/trackdetails";
    }
}
