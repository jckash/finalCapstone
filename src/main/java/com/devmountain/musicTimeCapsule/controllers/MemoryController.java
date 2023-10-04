package com.devmountain.musicTimeCapsule.controllers;

import com.devmountain.musicTimeCapsule.dtos.MemoriesDto;
import com.devmountain.musicTimeCapsule.entities.Memories;
import com.devmountain.musicTimeCapsule.services.MemoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/memories")
public class MemoryController {

    @Autowired
    private MemoriesService memoriesService;

    @GetMapping("user/{userId}")
    public List<MemoriesDto> getMemoriesByUser(@PathVariable Long userId){
        return memoriesService.getAllMemoriesByUserId(userId);
    }

    @PostMapping("/user/{userId}")
    public Memories addMemory(@RequestBody MemoriesDto memoriesDto, @PathVariable Long userId){
        return memoriesService.addMemory(memoriesDto, userId);
    }

    @DeleteMapping("/{memoryId}")
    public void deleteMemoryById(@PathVariable Long memoryId){
        memoriesService.deleteMemoryById(memoryId);
    }

    @PostMapping
    public void updateMemory(@RequestBody MemoriesDto memoriesDto){
        memoriesService.updateMemoryById(memoriesDto);
}

@GetMapping
public Optional<MemoriesDto> getMemoryById(@PathVariable Long memoryId){
        return memoriesService.getMemoriesById(memoryId);
}



}
