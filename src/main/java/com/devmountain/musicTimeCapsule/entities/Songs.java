package com.devmountain.musicTimeCapsule.entities;

import com.devmountain.musicTimeCapsule.dtos.SongsDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "songs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Songs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "song_title")
    private String songName;

    @Column(name = "artist_title")
    private String artist;

    @Column(name = "album_title")
    private String album;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "song_memories",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "memory_id")
    )
    private Set<Memories> memorySet = new HashSet<>();

    @ManyToOne
    @JsonBackReference
    private Users user;



    public Songs(long id, String songName, String artist, String album) {
        this.id = id;
        this.songName = songName;
        this.artist = artist;
        this.album = album;
    }



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
