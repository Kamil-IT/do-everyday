package com.doeveryday.doeverydayspotify.services;

import com.doeveryday.doeverydayspotify.exception.EmptyStringException;
import com.doeveryday.doeverydayspotify.exception.SpotifyApiException;
import com.doeveryday.doeverydayspotify.service.SpotifySearchService;
import com.doeveryday.doeverydayspotify.service.SpotifySearchServiceImpl;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;

class SpotifySearchServiceImplTest {

    public static final String NAME = "NameToSerach";

    @Mock
    private SpotifyApi spotifyApi;

    SpotifySearchService spotifySearchService;

    @BeforeEach
    void setUp() {
        spotifySearchService = new SpotifySearchServiceImpl(spotifyApi);

    }

    private SpotifyApi spotifyApiCreator() {
        //        Setting keys
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId("CLIENT_ID")
                .setClientSecret("CLIENT_SECRET")
                .build();

//        Create credentials
        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();

        try {
            final ClientCredentials clientCredentials;
            clientCredentials = clientCredentialsRequest.execute();

            // Set access token for further "spotifyApi" object usage
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());
            return spotifyApi;
        } catch (IOException | SpotifyWebApiException e) {
            e.printStackTrace();
            throw new SpotifyApiException(e.getMessage());
        }
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
}