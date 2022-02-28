package com.example.security.controller;


import com.example.security.entity.NoSqlItemEntity;
import com.example.security.service.NoSqlItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items2")
public class NoSqlItemController {

    @Autowired
    private NoSqlItemService noSqlItemService;

    @PostMapping()
    public ResponseEntity saveItem(@RequestParam("src")  String source2, @RequestBody NoSqlItemEntity item){
        if (source2.equals("source2")) {
            try {
                noSqlItemService.saveItem(item);
                return ResponseEntity.ok("Item сохранен");
            }catch (Exception e){
                return ResponseEntity.badRequest().body("Произошла ошибка");
            }
        }else {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
   @GetMapping()
    public ResponseEntity getItem(@RequestParam(value = "src") String source2){
        if (source2.equals("source2")) {
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
    public ResponseEntity getOneItems(@PathVariable("id") Long id,@RequestParam(value = "src2") @Value("source2") String source2){
        if (source2.equals("source2")) {
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
    public ResponseEntity getOneItemByParent(@PathVariable("parentId") Long parentId,@RequestParam(value = "src") String source2){
        if (source2.equals("source2")) {
            try {
                return ResponseEntity.ok(noSqlItemService.getOneItemByParent(parentId));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Произошла ошибка");
            }
        }else {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping(path = "{id}")
    public ResponseEntity updateItem(@PathVariable Long id, @RequestBody NoSqlItemEntity item,@RequestParam(value = "src") String source2){
        if (source2.equals("source2")) {
            try {
                return ResponseEntity.ok(noSqlItemService.updateItem(id, item));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Произошла ошибка");
            }
        }else {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
