package com.devmountain.musicTimeCapsule.services;

import com.devmountain.musicTimeCapsule.dtos.MemoriesDto;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface MemoriesService {
    @Transactional
    void addMemory(MemoriesDto memoriesDto, Long userId);

    @Transactional
    void deleteMemoryById(Long memoryId);

    @Transactional
    void updateMemoryById(MemoriesDto memoriesDto);

    //get memories
    List<MemoriesDto> getAllMemoriesByUserId(Long UserId);

    Optional<MemoriesDto> getMemoriesById(Long memoriesId);
}
