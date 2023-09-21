package com.devmountain.musicTimeCapsule.services;

import com.devmountain.musicTimeCapsule.repositories.SongsRepository;
import com.devmountain.musicTimeCapsule.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SongsServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SongsRepository songsRepository;
}
