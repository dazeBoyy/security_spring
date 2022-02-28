package com.example.security.service;

import com.example.security.entity.ItemEntity;
import com.example.security.entity.NoSqlItemEntity;
import com.example.security.exception.ItemAlreadyExist;
import com.example.security.exception.ItemNotFound;
import com.example.security.nosqlItemRepo.NoSqlItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class NoSqlItemService {

    @Autowired
    private NoSqlItemRepo noSqlItemService;

    @PostMapping
    public NoSqlItemEntity saveItem(NoSqlItemEntity item) throws ItemAlreadyExist {
        if (noSqlItemService.findByName(item.getName()) != null) {
            throw new ItemAlreadyExist("Ошибка пользователя");
        }
        return noSqlItemService.save(item);
    }
    @GetMapping
    public List<NoSqlItemEntity> getItem() throws ItemNotFound {
        if (noSqlItemService.findAll() == null){
            throw new ItemNotFound("Небыл найден");
        }

        return noSqlItemService.findAll();
    }

    @GetMapping
    public NoSqlItemEntity getOneItem(Long id) throws ItemNotFound {
        NoSqlItemEntity item = noSqlItemService.findById(id).get();
        if(item == null){
            throw new ItemNotFound("Небыл найден");
        }
        return item;
    }

    @GetMapping
    public NoSqlItemEntity getOneItemByParent(Long parentId) throws ItemNotFound {
        NoSqlItemEntity item = noSqlItemService.findByParentId(parentId);
        if(item == null){
            throw new ItemNotFound("Небыл найден");
        }
        return item;
    }

    @PutMapping
    public NoSqlItemEntity updateItem(@PathVariable Long id, @RequestBody NoSqlItemEntity item) throws ItemNotFound {
        NoSqlItemEntity updatedItem = noSqlItemService.findById(id).get();
        if (updatedItem == null) {
            throw new ItemNotFound("Небыл найден");
        }
        updatedItem.setName(item.getName());
        noSqlItemService.save(updatedItem);
        return updatedItem;
    }


}
