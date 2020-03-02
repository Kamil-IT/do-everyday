package com.doeveryday.doeverydayspotify.service;

import com.doeveryday.doeverydayspotify.exception.SpotifyApiException;
import com.doeveryday.doeverydayspotify.model.SpotifyAllTypes;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@AllArgsConstructor
@Service
public class SpotifySearchService {

    private final SpotifyApi spotifyApi;

    public SpotifyAllTypes spotifyAllTypes(String name){
        SpotifyAllTypes spotifyAllTypes = new SpotifyAllTypes();
        try {
            spotifyAllTypes.setAlbumSimplifieds(spotifyApi.searchAlbums(name).build().execute().getItems());
            spotifyAllTypes.setArtists(spotifyApi.searchArtists(name).build().execute().getItems());
            spotifyAllTypes.setTracks(spotifyApi.searchTracks(name).build().execute().getItems());

        } catch (IOException | SpotifyWebApiException e) {
            throw new SpotifyApiException("HTTP status code 4** or 5** has been returned in a request");
        }
        return spotifyAllTypes;
    }

    public SpotifyAllTypes spotifyAllTypes(String name, Integer limit){
        SpotifyAllTypes spotifyAllTypes = new SpotifyAllTypes();
        try {
            spotifyAllTypes.setAlbumSimplifieds(spotifyApi.searchAlbums(name).limit(limit).build().execute().getItems());
            spotifyAllTypes.setArtists(spotifyApi.searchArtists(name).limit(limit).build().execute().getItems());
            spotifyAllTypes.setTracks(spotifyApi.searchTracks(name).limit(limit).build().execute().getItems());

        } catch (IOException | SpotifyWebApiException e) {
            throw new SpotifyApiException("HTTP status code 4** or 5** has been returned in a request");
        }

        return spotifyAllTypes;
    }
}
