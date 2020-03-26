package com.doeveryday.doeverydayspotify.service;

import com.doeveryday.doeverydayspotify.exception.EmptyStringException;
import com.wrapper.spotify.SpotifyApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;

class SpotifySearchArtistServiceImplExceptionThrowsTest {

    public static final String NAME = "NameToSerach";

    @Mock
    private SpotifyApi spotifyApi;

    SpotifySearchArtistService spotifySearchArtistService;

    @BeforeEach
    void setUp() {
        spotifySearchArtistService = new SpotifySearchArtistServiceImpl(spotifyApi);

    }

    @Test
    void testGetArtistByNameOneArg_NullName() {
        assertThrows(NullPointerException.class, () -> spotifySearchArtistService.getArtistByName(null));
    }

    @Test
    void testGetArtistByNameOneArg_EmptyName() {
        assertThrows(EmptyStringException.class, () -> spotifySearchArtistService.getArtistByName(""));
    }


    @Test
    void testGetArtistByName_EmptyName() {
        assertThrows(EmptyStringException.class, () -> spotifySearchArtistService.getArtistByName("", anyInt()));
    }


    @Test
    void testGetArtistByName_NullName() {
        assertThrows(NullPointerException.class, () -> spotifySearchArtistService.getArtistByName(null, anyInt()));
    }


    @Test
    void testGetArtistByName_NullLimit() {
        assertThrows(NullPointerException.class, () -> spotifySearchArtistService.getArtistByName(NAME, null));
    }

    @Test
    void testGetArtistById_Null() {
        assertThrows(NullPointerException.class, () -> spotifySearchArtistService.getArtistById(null));
    }
}