package com.devmountain.musicTimeCapsule.entities;

import com.devmountain.musicTimeCapsule.dtos.UsersDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Users {

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @Column(unique = true)
    private String username;

   @Column
   private String password;

   @Column(unique = true)
   private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    private Set<Songs> songsSet = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    private Set<Memories> memoriesSet = new HashSet<>();



    public Users(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }




    public Users(UsersDto usersDto){
        if (usersDto.getUsername() != null){
            this.username = usersDto.getUsername();
        }
        if (usersDto.getPassword() != null){
            this.password = usersDto.getPassword();
        }
        if (usersDto.getEmail() != null){
            this.email = usersDto.getEmail();
        }

    }
}
