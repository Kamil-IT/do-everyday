package com.doeveryday.doeverydayspotify.service;

import com.wrapper.spotify.model_objects.specification.Artist;

import java.util.List;

public interface SpotifySearchArtistService {

    List<Artist> getArtistByName(String name);

    List<Artist> getArtistByName(String name, Integer limit);
}
