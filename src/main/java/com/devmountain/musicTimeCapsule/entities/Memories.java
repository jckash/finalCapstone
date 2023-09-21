package com.devmountain.musicTimeCapsule.entities;

import com.devmountain.musicTimeCapsule.dtos.MemoriesDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "Memories")
public class Memories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "Season")
    private String season;

    @Column(columnDefinition = "Memory")
    private String memory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public Memories() {
    }

    public Memories(Long id, String season, String memory) {
        this.id = id;
        this.season = season;
        this.memory = memory;
    }

    @ManyToOne
    @JsonBackReference
    private Users user;

    public Memories(MemoriesDto memoriesDto){

        if(memoriesDto.getSeason() != null){
            this.season = memoriesDto.getSeason();
        }
        if (memoriesDto.getMemory() != null){
            this.memory = memoriesDto.getMemory();
        }
    }

}
