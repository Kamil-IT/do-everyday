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
        if (albumSimplifieds == null){
            return new ArrayList<AlbumSimplified>();
        }
        return new ArrayList<>(Arrays.asList(albumSimplifieds));
    }

    public List<Track> getTracksList(){
        if (tracks == null){
            return new ArrayList<Track>();
        }
        return new ArrayList<>(Arrays.asList(tracks));
    }

    public List<Artist> getArtistsList(){
        if (artists == null){
            return new ArrayList<Artist>();
        }
        return new ArrayList<>(Arrays.asList(artists));
    }


}
