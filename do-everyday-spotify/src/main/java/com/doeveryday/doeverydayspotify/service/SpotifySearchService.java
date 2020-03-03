package com.doeveryday.doeverydayspotify.service;

import com.doeveryday.doeverydayspotify.model.SpotifyAllTypes;

public interface SpotifySearchService {

    SpotifyAllTypes spotifyAllTypesByName(String name);

    SpotifyAllTypes spotifyAllTypesByName(String name, Integer limit);
}
