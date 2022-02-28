package com.example.security.repository;

import com.example.security.entity.ItemEntity;
import com.example.security.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserEntity,Long> {
    UserEntity findByUsername(String username);
}
