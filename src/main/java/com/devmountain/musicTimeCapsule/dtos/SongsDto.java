package com.devmountain.musicTimeCapsule.dtos;

import com.devmountain.musicTimeCapsule.entities.Songs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongsDto implements Serializable {

    private Long id;
    private String songName;
    private String artist;
    private String album;
    private UsersDto usersDto;
    private MemoriesDto memoriesDto;


    public SongsDto(Songs songs){
        if (songs.getSongName() != null){
            this.songName = songs.getSongName();
        }
        if (songs.getArtist() != null){
            this.artist = songs.getArtist();
        }
        if (songs.getAlbum() != null){
            this.album = songs.getAlbum();
        }
    }
}
