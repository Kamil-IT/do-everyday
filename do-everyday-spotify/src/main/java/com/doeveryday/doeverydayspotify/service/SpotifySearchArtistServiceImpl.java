package com.doeveryday.doeverydayspotify.service;

import com.doeveryday.doeverydayspotify.exception.SpotifyApiException;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Service
public class SpotifySearchArtistServiceImpl implements SpotifySearchArtistService{

    private final SpotifyApi spotifyApi;

    public List<Artist> getArtistByName(String name){
        List<Artist> artists = new ArrayList<>();
        try {
            Artist[] items = spotifyApi.searchArtists(name).build().execute().getItems();
            artists.addAll(Arrays.asList(items));
        } catch (IOException | SpotifyWebApiException e) {
            throw new SpotifyApiException("HTTP status code 4** or 5** has been returned in a request");
        }
        return artists;
    }

    public List<Artist> getArtistByName(String name, Integer limit){
        List<Artist> albums = new ArrayList<>();
        try {
            Artist[] items = spotifyApi.searchArtists(name).limit(limit).build().execute().getItems();
            albums.addAll(Arrays.asList(items));
        } catch (IOException | SpotifyWebApiException e) {
            throw new SpotifyApiException("HTTP status code 4** or 5** has been returned in a request");
        }
        return albums;
    }

    @Override
    public Artist getArtistById(String id) {
        try {
            return spotifyApi.getArtist(id).build().execute();
        } catch (IOException | SpotifyWebApiException e) {
            throw new SpotifyApiException("HTTP status code 4** or 5** has been returned in a request");
        }
    }
}
