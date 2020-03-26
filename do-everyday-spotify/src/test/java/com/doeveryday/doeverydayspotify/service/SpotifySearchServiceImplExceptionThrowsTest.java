package com.doeveryday.doeverydayspotify.service;

import com.doeveryday.doeverydayspotify.exception.EmptyStringException;
import com.wrapper.spotify.SpotifyApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;

class SpotifySearchServiceImplExceptionThrowsTest {

    public static final String NAME = "NameToSerach";

    @Mock
    private SpotifyApi spotifyApi;

    SpotifySearchService spotifySearchService;

    @BeforeEach
    void setUp() {
        spotifySearchService = new SpotifySearchServiceImpl(spotifyApi);

    }

    @Test
    void testSpotifyAllTypesByName_EmptyName() {
        assertThrows(EmptyStringException.class, () -> spotifySearchService.spotifyAllTypesByName(""));
    }

    @Test
    void testSpotifyAllTypesByName_NullName() {
        assertThrows(NullPointerException.class, () -> spotifySearchService.spotifyAllTypesByName(null));
    }

    @Test
    void testSpotifyAllTypesByNameToArg_EmptyName() {
        assertThrows(EmptyStringException.class, () -> spotifySearchService.spotifyAllTypesByName("", anyInt()));
    }

    @Test
    void testSpotifyAllTypesByNameToArg_NullName() {
        assertThrows(NullPointerException.class, () -> spotifySearchService.spotifyAllTypesByName(null, anyInt()));
    }

    @Test
    void testSpotifyAllTypesByNameToArg_NullLimit() {
        assertThrows(NullPointerException.class, () -> spotifySearchService.spotifyAllTypesByName(NAME, null));
    }
}