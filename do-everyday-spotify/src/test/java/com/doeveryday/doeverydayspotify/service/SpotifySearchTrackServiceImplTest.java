package com.doeveryday.doeverydayspotify.service;

import com.doeveryday.doeverydayspotify.exception.EmptyStringException;
import com.wrapper.spotify.SpotifyApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;

class SpotifySearchTrackServiceImplTest {

    public static final String NAME = "NameToSerach";

    @Mock
    private SpotifyApi spotifyApi;

    SpotifySearchTrackService spotifySearchTrackService;

    @BeforeEach
    void setUp() {
        spotifySearchTrackService = new SpotifySearchTrackServiceImpl(spotifyApi);

    }


    @Test
    void testGetTrackByNameOneArg_NullName() {
        assertThrows(NullPointerException.class, () -> spotifySearchTrackService.getTracksByName(null));
    }

    @Test
    void testGetTrackByNameOneArg_EmptyName() {
        assertThrows(EmptyStringException.class, () -> spotifySearchTrackService.getTracksByName(""));
    }


    @Test
    void testGetTrackByName_EmptyName() {
        assertThrows(EmptyStringException.class, () -> spotifySearchTrackService.getTracksByName("", anyInt()));
    }


    @Test
    void testGetTrackByName_NullName() {
        assertThrows(NullPointerException.class, () -> spotifySearchTrackService.getTracksByName(null, anyInt()));
    }


    @Test
    void testGetTrackByName_NullLimit() {
        assertThrows(NullPointerException.class, () -> spotifySearchTrackService.getTracksByName(NAME, null));
    }

    @Test
    void testGetTrackById_Null() {
        assertThrows(NullPointerException.class, () -> spotifySearchTrackService.getTrackById(null));
    }
}