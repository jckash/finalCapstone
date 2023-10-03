package com.devmountain.musicTimeCapsule.entities;

import com.devmountain.musicTimeCapsule.dtos.MemoriesDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "memories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Memories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "season")
    private String season;

    @Column(name = "memory")
    private String memory;

    @ManyToMany(mappedBy = "memorySet", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.PERSIST})
    @JsonBackReference
    private Set<Songs> setOfSongs;

    @ManyToOne
    @JsonBackReference
    private Users user;


    public Memories(Long id, String season, String memory) {
        this.id = id;
        this.season = season;
        this.memory = memory;
    }



    public Memories(MemoriesDto memoriesDto){

        if(memoriesDto.getSeason() != null){
            this.season = memoriesDto.getSeason();
        }
        if (memoriesDto.getMemory() != null){
            this.memory = memoriesDto.getMemory();
        }
    }

}
