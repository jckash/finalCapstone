package com.devmountain.musicTimeCapsule.controllers;

import com.devmountain.musicTimeCapsule.dtos.UsersDto;
import com.devmountain.musicTimeCapsule.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public List<String> addUser(@RequestBody UsersDto usersDto) {
        String passHash = passwordEncoder.encode(usersDto.getPassword());
        usersDto.setPassword(passHash);
        return userService.addUser(usersDto);
    }

    @PostMapping("/login")
    public List<String> userLogin(@RequestBody UsersDto usersDto){
        return userService.userLogin(usersDto);

    }




}


