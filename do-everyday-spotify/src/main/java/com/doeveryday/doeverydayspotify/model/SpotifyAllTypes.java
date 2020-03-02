package com.doeveryday.doeverydayspotify.model;

import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Track;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/*
Class with keep tracks, albumSimplifieds and artists(use only when you have all 3 types of data)
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class SpotifyAllTypes {
    AlbumSimplified[] albumSimplifieds;
    Track[] tracks;
    Artist[]artists;

    public List<AlbumSimplified> getAlbumSimplifiedsList(){
        return new ArrayList<>(Arrays.asList(albumSimplifieds));
    }

    public List<Track> getTracksList(){
        return new ArrayList<>(Arrays.asList(tracks));
    }

    public List<Artist> getArtistsList(){
        return new ArrayList<>(Arrays.asList(artists));
    }


}
