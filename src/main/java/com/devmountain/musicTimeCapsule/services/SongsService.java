package com.devmountain.musicTimeCapsule.services;

import com.devmountain.musicTimeCapsule.dtos.SongsDto;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface SongsService {
    @Transactional
    void addSong(SongsDto songsDto, Long userId);

    @Transactional
    void deleteSongById(Long songsId);

    //Update the parts of the song
    @Transactional
    void updateSongById(SongsDto songsDto);

    List<SongsDto> getAllSongsByUserId(Long userId);

    Optional<SongsDto> getSongById(Long songId);
}



