package com.devmountain.musicTimeCapsule.services;

import com.devmountain.musicTimeCapsule.dtos.UsersDto;

import java.util.List;

public interface UserService {
    List<String> addUser(UsersDto usersDto);

    List<String> userLogin(UsersDto usersDto);
}
