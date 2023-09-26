package com.devmountain.musicTimeCapsule.services;

import com.devmountain.musicTimeCapsule.dtos.MemoriesDto;
import com.devmountain.musicTimeCapsule.entities.Memories;
import com.devmountain.musicTimeCapsule.entities.Users;
import com.devmountain.musicTimeCapsule.repositories.MemoriesRepository;
import com.devmountain.musicTimeCapsule.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemoriesServiceImpl implements MemoriesService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MemoriesRepository memoriesRepository;

    @Override
    @Transactional
public void addMemory(MemoriesDto memoriesDto, Long userId){
    Optional<Users> usersOptional = userRepository.findById(userId);
    Memories memories = new Memories(memoriesDto);
    usersOptional.ifPresent(memories::setUser);
    memoriesRepository.saveAndFlush(memories);
}

@Override
@Transactional
public void deleteMemoryById(Long memoryId) {
    Optional<Memories> memoriesOptional = memoriesRepository.findById(memoryId);
    memoriesOptional.ifPresent(memories -> memoriesRepository.delete(memories));
}

@Override
@Transactional
public void updateMemoryById(MemoriesDto memoriesDto){
        Optional<Memories> memoriesOptional = memoriesRepository.findById(memoriesDto.getId());
        memoriesOptional.ifPresent(memories -> {
            memories.setMemory(memoriesDto.getMemory());
            memoriesRepository.saveAndFlush(memories);
            memories.setSeason(memoriesDto.getSeason());
            memoriesRepository.saveAndFlush(memories);
        });
}

//get memories
@Override
public List<MemoriesDto> getAllMemoriesByUserId(Long userId){
        Optional<Users> usersOptional = userRepository.findById(userId);
        if (usersOptional.isPresent()){
            List<Memories> memoriesList = memoriesRepository.findAllByUserEquals(usersOptional.get());
            return memoriesList.stream().map(memories -> new MemoriesDto(memories)).collect(Collectors.toList());
        }
        return Collections.emptyList();
}

@Override
public Optional<MemoriesDto> getMemoriesById(Long memoriesId) {
        Optional<Memories> memoriesOptional = memoriesRepository.findById(memoriesId);
        if (memoriesOptional.isPresent()){
            return Optional.of(new MemoriesDto(memoriesOptional.get()));
        }
        return Optional.empty();
}










}
