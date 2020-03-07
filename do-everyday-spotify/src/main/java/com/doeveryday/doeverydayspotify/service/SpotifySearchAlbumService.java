package com.doeveryday.doeverydayspotify.service;

import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;

import java.util.List;

public interface SpotifySearchAlbumService {

    List<AlbumSimplified> getAlbumByName(String name);

    List<AlbumSimplified> getAlbumByName(String name, Integer limit);

    Album getAlbumById(String id);

    }
