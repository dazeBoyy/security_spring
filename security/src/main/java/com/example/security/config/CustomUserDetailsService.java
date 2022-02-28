package com.example.security.config;

import com.example.security.entity.UserEntity;
import com.example.security.exception.ItemNotFound;
import com.example.security.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByUsername(username);
        try {
            if (user == null) {
                throw new ItemNotFound("Небыл найден");
            }
        } catch (ItemNotFound e) {
            e.printStackTrace();
        }

        return new CustomUserDetails(user);
    }
}
