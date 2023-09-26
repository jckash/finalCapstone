package com.devmountain.musicTimeCapsule.entities;

import com.devmountain.musicTimeCapsule.dtos.SongsDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Songs")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Songs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "Song Title")
    private String songName;

    @Column(columnDefinition = "Artist Name")
    private String artist;

    @Column(columnDefinition = "Album Title")
    private String album;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }


    public Songs(long id, String songName, String artist, String album) {
        this.id = id;
        this.songName = songName;
        this.artist = artist;
        this.album = album;
    }

    @ManyToOne
    @JsonBackReference
    private Users user;

    public Songs(SongsDto songsDto) {
        if (songsDto.getSongName() != null) {
            this.songName = songsDto.getSongName();
        }
        if (songsDto.getArtist() != null) {
            this.artist = songsDto.getArtist();
        }
        if (songsDto.getAlbum() != null) {
            this.album = songsDto.getAlbum();
        }
    }
}
