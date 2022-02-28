package com.example.security.controller;


import com.example.security.entity.ItemEntity;
import com.example.security.entity.NoSqlItemEntity;
import com.example.security.nosqlItemRepo.Item;
import com.example.security.service.ItemService;
import com.example.security.service.NoSqlItemService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private NoSqlItemService noSqlItemService;

    @PostMapping()
    public ResponseEntity saveItem(@RequestParam("src") String source, @RequestBody ItemEntity item){
        if (source.equals("source1")) {
            try {
                itemService.saveItem(item);
                return ResponseEntity.ok("Item сохранен");
            }catch (Exception e){
                return ResponseEntity.badRequest().body("Произошла ошибка");
            }
        }else {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

   @GetMapping()
    public ResponseEntity getItem(@RequestParam(value = "src") @Value("source1") String source){
        if (source.equals("source1")) {
            try {
                return ResponseEntity.ok(itemService.getItem());
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Произошла ошибка");
            }
        }else if (source.equals("source2")) {
            try {
                return ResponseEntity.ok(noSqlItemService.getItem());
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Произошла ошибка");
            }
        }else {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping(path = "{id}")
    public ResponseEntity getOneItem(@PathVariable("id") Long id,@RequestParam(value = "src") String source){
        if (source.equals("source1")) {
            try {
                return ResponseEntity.ok(itemService.getOneItem(id));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Произошла ошибка");
            }
        } else  if (source.equals("source2")) {
            try {
                return ResponseEntity.ok(noSqlItemService.getOneItem(id));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Произошла ошибка");
            }
        } else{
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping(path = "{parentId}/children")
    public ResponseEntity getOneItemByParent(@PathVariable("parentId") Long parentId,@RequestParam(value = "src") String source1){
        if (source1.equals("source1")) {
            try {
                return ResponseEntity.ok(itemService.getOneItemByParent(parentId));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Произошла ошибка");
            }
        } else  if (source1.equals("source2")) {
            try {
                return ResponseEntity.ok(noSqlItemService.getOneItemByParent(parentId));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Произошла ошибка");
            }
        } else{
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping(path = "{id}")
    public ResponseEntity updateItem(@PathVariable Long id, @RequestBody ItemEntity item,@RequestParam(value = "src") String source1){
        if (source1.equals("source1")) {
            try {
                return ResponseEntity.ok(itemService.updateItem(id, item));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Произошла ошибка");
            }
        }else {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
