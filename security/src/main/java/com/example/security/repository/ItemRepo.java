package com.example.security.repository;

import com.example.security.entity.ItemEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ItemRepo extends CrudRepository<ItemEntity, Long> {

    ItemEntity findByName(String name);
    List<ItemEntity> findAll();
    ItemEntity findByParentId(Long parentId);
}
