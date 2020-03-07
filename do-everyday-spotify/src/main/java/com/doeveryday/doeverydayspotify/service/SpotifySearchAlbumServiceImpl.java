package com.doeveryday.doeverydayspotify.service;

import com.doeveryday.doeverydayspotify.exception.SpotifyApiException;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Service
public class SpotifySearchAlbumServiceImpl implements SpotifySearchAlbumService{

    private final SpotifyApi spotifyApi;

    public List<AlbumSimplified> getAlbumByName(String name){
        List<AlbumSimplified> albums = new ArrayList<>();
        try {
            AlbumSimplified[] items = spotifyApi.searchAlbums(name).build().execute().getItems();
            albums.addAll(Arrays.asList(items));
        } catch (IOException | SpotifyWebApiException e) {
            throw new SpotifyApiException("HTTP status code 4** or 5** has been returned in a request");
        }
        return albums;
    }

    public List<AlbumSimplified> getAlbumByName(String name, Integer limit){
        List<AlbumSimplified> albums = new ArrayList<>();
        try {
            AlbumSimplified[] items = spotifyApi.searchAlbums(name).limit(limit).build().execute().getItems();
            albums.addAll(Arrays.asList(items));
        } catch (IOException | SpotifyWebApiException e) {
            throw new SpotifyApiException("HTTP status code 4** or 5** has been returned in a request");
        }
        return albums;
    }

    @Override
    public Album getAlbumById(String id) {
        try {
            return spotifyApi.getAlbum(id).build().execute();
        } catch (IOException | SpotifyWebApiException e) {
            throw new SpotifyApiException("HTTP status code 4** or 5** has been returned in a request");
        }
    }
}
