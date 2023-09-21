package com.devmountain.musicTimeCapsule.dtos;

import com.devmountain.musicTimeCapsule.entities.Memories;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoriesDto implements Serializable {


    private Long id;
    private String season;
    private String memory;
    private UsersDto usersDto;
    private SongsDto songsDto;

    public MemoriesDto(Memories memories){
        if (memories.getId() != null){
            this.id = memories.getId();
        }
        if(memories.getSeason() != null){
            this.season = memories.getSeason();
        }
        if (memories.getMemory() != null){
            this.memory = memories.getMemory();
        }
    }

}
