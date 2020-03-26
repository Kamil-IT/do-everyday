package com.doeveryday.doeverydayspotify.service;

import com.doeveryday.doeverydayspotify.exception.EmptyStringException;
import com.wrapper.spotify.SpotifyApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;

class SpotifySearchAlbumServiceImplExceptionThrowsTest {


    public static final String NAME = "NameToSerach";

    @Mock
    private SpotifyApi spotifyApi;

    SpotifySearchAlbumService spotifySearchAlbumServiceImpl;

    @BeforeEach
    void setUp() {
        spotifySearchAlbumServiceImpl = new SpotifySearchAlbumServiceImpl(spotifyApi);
    }

    @Test
    void testGetAlbumByNameOneArg_NullName() {
        assertThrows(NullPointerException.class, () -> spotifySearchAlbumServiceImpl.getAlbumByName(null));
    }

    @Test
    void testGetAlbumByNameOneArg_EmptyName() {
        assertThrows(EmptyStringException.class, () -> spotifySearchAlbumServiceImpl.getAlbumByName(""));
    }


    @Test
    void testGetAlbumByName_EmptyName() {
        assertThrows(EmptyStringException.class, () -> spotifySearchAlbumServiceImpl.getAlbumByName("", anyInt()));
    }


    @Test
    void testGetAlbumByName_NullName() {
        assertThrows(NullPointerException.class, () -> spotifySearchAlbumServiceImpl.getAlbumByName(null, anyInt()));
    }


    @Test
    void testGetAlbumByName_NullLimit() {
        assertThrows(NullPointerException.class, () -> spotifySearchAlbumServiceImpl.getAlbumByName(NAME, null));
    }

    @Test
    void testGetAlbumById_Null() {
        assertThrows(NullPointerException.class, () -> spotifySearchAlbumServiceImpl.getAlbumById(null));
    }
}