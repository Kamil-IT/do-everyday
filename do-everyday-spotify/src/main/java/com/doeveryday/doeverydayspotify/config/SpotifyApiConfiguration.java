package com.doeveryday.doeverydayspotify.config;

import com.doeveryday.doeverydayspotify.exception.SpotifyApiException;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Slf4j
@Configuration
public class SpotifyApiConfiguration {

    @Value("${spotify.api.client.id}")
    private String CLIENT_ID;

    @Value("${spotify.api.client.secret}")
    private String CLIENT_SECRET;

    @Bean
    public SpotifyApi spotifyApi() {

//        Setting keys
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(CLIENT_ID)
                .setClientSecret(CLIENT_SECRET)
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
            log.error(e.getMessage());
            e.printStackTrace();
            throw new SpotifyApiException(e.getMessage());
        }
    }
}
