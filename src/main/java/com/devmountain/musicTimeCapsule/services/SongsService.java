package com.devmountain.musicTimeCapsule.services;

import com.devmountain.musicTimeCapsule.dtos.SongsDto;
import jakarta.transaction.Transactional;

public interface SongsService {
    @Transactional
    void addSong(SongsDto songsDto, Long userId);

    @Transactional
    void deleteNoteById(Long songsId);

    //Update the parts of the song
    @Transactional
    void updateSongById(SongsDto songsDto);
}
