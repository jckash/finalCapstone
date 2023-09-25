package com.devmountain.musicTimeCapsule.services;

import com.devmountain.musicTimeCapsule.dtos.SongsDto;
import com.devmountain.musicTimeCapsule.entities.Songs;
import com.devmountain.musicTimeCapsule.entities.Users;
import com.devmountain.musicTimeCapsule.repositories.SongsRepository;
import com.devmountain.musicTimeCapsule.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class SongsServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SongsRepository songsRepository;


public void addSong(SongsDto songsDto, Long userId){
    Optional<Users> usersOptional = userRepository.findById(userId);
    Songs songs = new Songs(songsDto);
    usersOptional.ifPresent(songs::setUser);
    songsRepository.saveAndFlush(songs);

}


}
