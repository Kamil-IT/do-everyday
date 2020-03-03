package com.doeveryday.doeverydayspotify.baseClass;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.miscellaneous.CurrentlyPlaying;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@Component
public class SpotifyClientSearcher {
    private final SpotifyApi spotifyApi;

    public SpotifyClientSearcher(SpotifyApi spotifyApi) {
        this.spotifyApi = spotifyApi;


        try {
            Paging<Track> tracks = spotifyApi.searchTracks("repeat").limit(10).build().execute();


        } catch (IOException | SpotifyWebApiException e) {
            e.printStackTrace();
        }

    }
}
