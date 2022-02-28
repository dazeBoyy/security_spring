package com.example.security.service;


import com.example.security.config.CustomUserDetails;
import com.example.security.entity.UserEntity;
import com.example.security.exception.ItemAlreadyExist;
import com.example.security.exception.ItemNotFound;
import com.example.security.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;



    @PostMapping
    public UserEntity createUser(UserEntity user) throws ItemAlreadyExist {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            throw new ItemAlreadyExist("Пользователь уже существует");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepo.save(user);
    }



    @PutMapping
    public UserEntity updateUser(@PathVariable Long id, @RequestBody UserEntity user) throws ItemNotFound {
        UserEntity updatedUser = userRepo.findById(id).get();
        if (updatedUser == null) {
            throw new ItemNotFound("Небыл найден");
        }
        updatedUser.setUsername(user.getUsername());
        updatedUser.setPassword(user.getPassword());
        userRepo.save(updatedUser);
        return updatedUser;
    }

    @DeleteMapping
    public Long deleteUser(@PathVariable Long id){
        userRepo.deleteById(id);
        return id;
    }

    @GetMapping
    public UserEntity getOneUser(Long id) throws ItemNotFound {
        UserEntity user = userRepo.findById(id).get();
        if(user == null){
            throw new ItemNotFound("Небыл найден");
        }
        return user;
    }


}
