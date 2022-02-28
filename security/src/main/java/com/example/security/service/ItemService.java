package com.example.security.service;


import com.example.security.entity.ItemEntity;
import com.example.security.exception.ItemAlreadyExist;
import com.example.security.exception.ItemNotFound;
import com.example.security.nosqlItemRepo.NoSqlItemRepo;
import com.example.security.repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Service
public class ItemService {

    @Autowired
    private ItemRepo itemRepo;


    @PostMapping
    public ItemEntity saveItem(ItemEntity item) throws ItemAlreadyExist {
        if (itemRepo.findByName(item.getName()) != null) {
         throw new ItemAlreadyExist("Ошибка пользователя");
        }
        return itemRepo.save(item);
    }
    @GetMapping
    public List<ItemEntity> getItem() throws ItemNotFound {
       if (itemRepo.findAll() == null){
           throw new ItemNotFound("Небыл найден");
       }

       return itemRepo.findAll();
    }

    @GetMapping
    public ItemEntity getOneItem(Long id) throws ItemNotFound {
        ItemEntity item = itemRepo.findById(id).get();
        if(item == null){
            throw new ItemNotFound("Небыл найден");
        }
        return item;
    }

    @GetMapping
    public ItemEntity getOneItemByParent(Long parentId) throws ItemNotFound {
        ItemEntity item = itemRepo.findByParentId(parentId);
        if(item == null){
            throw new ItemNotFound("Небыл найден");
        }
        return item;
    }

    @PutMapping
    public ItemEntity updateItem(@PathVariable Long id, @RequestBody ItemEntity item) throws ItemNotFound {
        ItemEntity updatedItem = itemRepo.findById(id).get();
        if (updatedItem == null) {
            throw new ItemNotFound("Небыл найден");
        }
        updatedItem.setName(item.getName());
        itemRepo.save(updatedItem);
        return updatedItem;
    }
}
