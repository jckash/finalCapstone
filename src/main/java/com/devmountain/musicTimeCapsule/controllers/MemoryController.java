package com.devmountain.musicTimeCapsule.controllers;

import com.devmountain.musicTimeCapsule.dtos.MemoriesDto;
import com.devmountain.musicTimeCapsule.services.MemoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class MemoryController {

    @Autowired
    private MemoriesService memoriesService;

    @GetMapping("user/{userId}")
    public List<MemoriesDto> getMemoriesByUser(Long userId){
        return memoriesService.getAllMemoriesByUserId(userId);
    }

    @PostMapping("/user/{userId}")
    public void addMemory(@RequestBody MemoriesDto memoriesDto, @PathVariable Long userId){
        memoriesService.addMemory(memoriesDto, userId);
    }

    @DeleteMapping("/{memoryId}")
    public void deleteMemoryById(@PathVariable Long memoryId){
        memoriesService.deleteMemoryById(memoryId);
    }



}
