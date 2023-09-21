package com.devmountain.musicTimeCapsule.services;


import com.devmountain.musicTimeCapsule.dtos.UsersDto;
import com.devmountain.musicTimeCapsule.entities.Users;
import com.devmountain.musicTimeCapsule.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<String> addUser(UsersDto usersDto) {
        List<String> response = new ArrayList<>();
        Users users = new Users(usersDto);
        userRepository.saveAndFlush(users);
        response.add("User Added Successfully");
        return response;
    }

    @Override
    public List<String> userLogin(UsersDto usersDto) {
        List<String> response = new ArrayList<>();
        Optional<Users> usersOptional = userRepository.findByUsername(usersDto.getUsername());
        if (usersOptional.isPresent()) {
            if (passwordEncoder.matches(usersDto.getPassword(), usersOptional.get().getPassword())) {
                response.add("User Login Successful");
                response.add(String.valueOf(usersOptional.get().getId()));
            } else {
                response.add("Username or password incorrect");
            }
        } else {response.add("Username or password incorrect");
        }
        return response;
    }

}
