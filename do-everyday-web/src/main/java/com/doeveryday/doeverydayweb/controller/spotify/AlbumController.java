package com.doeveryday.doeverydayweb.controller.spotify;

import com.doeveryday.doeverydayspotify.service.SpotifySearchAlbumService;
import com.wrapper.spotify.model_objects.specification.Album;
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
public class AlbumController {

    private final SpotifySearchAlbumService spotifySearchAlbumService;

    @GetMapping("spotify/album/{id}/details")
    public String getAlbumDetails(Model model, @PathVariable("id") String id){
        Album album = spotifySearchAlbumService.getAlbumById(id);
        model.addAttribute("album", album);
        model.addAttribute("artists", Arrays.asList(album.getArtists()));
        model.addAttribute("tracks", Arrays.asList(album.getTracks().getItems()));
        return "spotify/search/album/albumdetails";
    }
}
