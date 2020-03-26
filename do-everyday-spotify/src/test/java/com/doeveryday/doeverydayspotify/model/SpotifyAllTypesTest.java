package com.doeveryday.doeverydayspotify.model;

import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpotifyAllTypesTest {

    SpotifyAllTypes spotifyAllTypes;

    @BeforeEach
    void setUp() {
        spotifyAllTypes = new SpotifyAllTypes();

    }

    @Test
    void getAlbumSimplifiedsList() {
        AlbumSimplified[] albumSimplifieds = new AlbumSimplified[5];
        spotifyAllTypes.setAlbumSimplifieds(albumSimplifieds);
        assertEquals(5, spotifyAllTypes.getAlbumSimplifiedsList().size());
        assertEquals(0, spotifyAllTypes.getArtistsList().size());
        assertEquals(0, spotifyAllTypes.getTracksList().size());
    }

    @Test
    void getTracksList() {
        Artist[] artist = new Artist[5];
        spotifyAllTypes.setArtists(artist);
        assertEquals(0, spotifyAllTypes.getAlbumSimplifiedsList().size());
        assertEquals(5, spotifyAllTypes.getArtistsList().size());
        assertEquals(0, spotifyAllTypes.getTracksList().size());
    }

    @Test
    void getArtistsList() {
        Track[] tracks = new Track[5];
        spotifyAllTypes.setTracks(tracks);
        assertEquals(0, spotifyAllTypes.getAlbumSimplifiedsList().size());
        assertEquals(0, spotifyAllTypes.getArtistsList().size());
        assertEquals(5, spotifyAllTypes.getTracksList().size());
    }
}