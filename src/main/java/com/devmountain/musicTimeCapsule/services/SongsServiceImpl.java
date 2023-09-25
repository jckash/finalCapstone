package com.devmountain.musicTimeCapsule.services;

import com.devmountain.musicTimeCapsule.dtos.SongsDto;
import com.devmountain.musicTimeCapsule.entities.Songs;
import com.devmountain.musicTimeCapsule.entities.Users;
import com.devmountain.musicTimeCapsule.repositories.SongsRepository;
import com.devmountain.musicTimeCapsule.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.hibernate.engine.internal.Collections;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SongsServiceImpl implements SongsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SongsRepository songsRepository;

@Override
@Transactional
public void addSong(SongsDto songsDto, Long userId){
    Optional<Users> usersOptional = userRepository.findById(userId);
    Songs songs = new Songs(songsDto);
    usersOptional.ifPresent(songs::setUser);
    songsRepository.saveAndFlush(songs);

}

@Override
@Transactional
public void deleteNoteById(Long songsId){
    Optional<Songs> songsOptional = songsRepository.findById(songsId);
    songsOptional.ifPresent(songs -> songsRepository.delete(songs));
}

//Update the parts of the song
@Override
@Transactional
public void updateSongById(SongsDto songsDto){
    Optional<Songs> songsOptional = songsRepository.findById(songsDto.getId());
    songsOptional.ifPresent(songs -> {
        songs.setSongName(songsDto.getSongName());
        songsRepository.saveAndFlush(songs);
        songs.setAlbum(songsDto.getAlbum());
        songsRepository.saveAndFlush(songs);
        songs.setArtist(songsDto.getArtist());
        songsRepository.saveAndFlush(songs);
    });


    //find songs by user. find songs by Song ID

    public List<SongsDto> getAllSongsByUserId(Long userId){
        Optional<Users> usersOptional = userRepository.findById(userId);
        if (usersOptional.isPresent()) {
            List<Songs> songsList = songsRepository.findAllByUserEquals(usersOptional.get());
            return songsList.stream().map(songs -> new SongsDto(songs)).collect(Collectors.toList());
        }
        return Collections.emptyList();
        }

        public Optional<SongsDto> getSongById(Long songsId) {
        Optional<Songs> songsOptional1 = songsRepository.findById(songId);
        if (songsOptional1.isPresent()){
            return Optional.of(new SongsDto(songsOptional1.get()));
        }
        return Optional.empty();

    }

}



}
