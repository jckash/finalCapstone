package com.devmountain.musicTimeCapsule.dtos;


import com.devmountain.musicTimeCapsule.entities.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto implements Serializable {

    private Long id;
    private String username;
    private String password;
    private String email;
    private Set<SongsDto> songsDtoSet = new HashSet<>();
    private Set<MemoriesDto> memoriesDtoSet = new HashSet<>();

    public UsersDto(Users user){
        if(user.getId() != null){
            this.id = user.getId();
        }
        if (user.getUsername() != null){
            this.username = user.getUsername();
        }
        if (user.getPassword() != null){
            this.password = user.getPassword();
        }
        if(user.getEmail() != null){
            this.email = user.getEmail();
        }
    }

}
