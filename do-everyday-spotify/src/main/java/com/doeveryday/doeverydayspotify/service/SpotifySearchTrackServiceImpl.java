package com.doeveryday.doeverydayspotify.service;

import com.doeveryday.doeverydayspotify.exception.EmptyStringException;
import com.doeveryday.doeverydayspotify.exception.SpotifyApiException;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Track;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Service
public class SpotifySearchTrackServiceImpl implements SpotifySearchTrackService {

    private final SpotifyApi spotifyApi;

    public List<Track> getTracksByName(String name){
        if (name.length() == 0){
            throw new EmptyStringException("Name cannot have o length");
        }
        List<Track> tracks = new ArrayList<>();
        try {
            Track[] items = spotifyApi.searchTracks(name).build().execute().getItems();
            tracks.addAll(Arrays.asList(items));
        } catch (IOException | SpotifyWebApiException e) {
            throw new SpotifyApiException("HTTP status code 4** or 5** has been returned in a request");
        }
        return tracks;
    }

    public List<Track> getTracksByName(String name, Integer limit){
        if (name.length() == 0){
            throw new EmptyStringException("Name cannot have o length");
        }
        List<Track> tracks = new ArrayList<>();
        try {
            Track[] items = spotifyApi.searchTracks(name).limit(limit).build().execute().getItems();
            tracks.addAll(Arrays.asList(items));
        } catch (IOException | SpotifyWebApiException e) {
            throw new SpotifyApiException("HTTP status code 4** or 5** has been returned in a request");
        }
        return tracks;
    }

    @Override
    public Track getTrackById(String id) {
        try {
            return spotifyApi.getTrack(id).build().execute();
        } catch (IOException | SpotifyWebApiException e) {
            throw new SpotifyApiException("HTTP status code 4** or 5** has been returned in a request");
        }
    }

}
