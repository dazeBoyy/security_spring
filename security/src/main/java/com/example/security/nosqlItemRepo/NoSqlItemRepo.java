package com.example.security.nosqlItemRepo;


import com.example.security.entity.NoSqlItemEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NoSqlItemRepo extends MongoRepository<NoSqlItemEntity, Long> {
    NoSqlItemEntity findByName(String name);
    NoSqlItemEntity findByParentId(Long parentId);
}
