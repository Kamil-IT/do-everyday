package com.doeveryday.doeverydayspotify.service;

import com.wrapper.spotify.model_objects.specification.Track;

import java.util.List;

public interface SpotifySearchTrackService {

    List<Track> getTracksByName(String name);

    List<Track> getTracksByName(String name, Integer limit);
}
