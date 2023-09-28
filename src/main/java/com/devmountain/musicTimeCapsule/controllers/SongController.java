package com.devmountain.musicTimeCapsule.controllers;

import com.devmountain.musicTimeCapsule.dtos.SongsDto;
import com.devmountain.musicTimeCapsule.services.SongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/songs")
public class SongController {
    @Autowired
    private SongsService songsService;

    @GetMapping("/user/{userId}")
    public List<SongsDto> getSongsByUser(@PathVariable Long userId){
        return songsService.getAllSongsByUserId(userId);

    }
@PostMapping("/user/{userId}")
    public void addSong(@RequestBody SongsDto songsDto, @PathVariable Long userId){
        songsService.addSong(songsDto, userId);
    }

    @DeleteMapping("/{songId}")
    public void deleteSongById(@PathVariable Long songId){
        songsService.deleteSongById(songId);
    }

    @PostMapping
    public void updateSong(@RequestBody SongsDto songsDto){
        songsService.updateSongById(songsDto);
    }

    public Optional<SongsDto> getSongById(Long songId){
        return songsService.getSongById(songId);
    }


}
