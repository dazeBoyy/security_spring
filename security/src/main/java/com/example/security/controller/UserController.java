package com.example.security.controller;



import com.example.security.entity.UserEntity;
import com.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody UserEntity user){
        try {
            userService.createUser(user);
            return ResponseEntity.ok("User сохранен");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping(path = "{id}")
    public ResponseEntity updateItem(@PathVariable Long id, @RequestBody UserEntity user){
        try {
            return ResponseEntity.ok(userService.updateUser(id,user));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        try {
            return ResponseEntity.ok(userService.deleteUser(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping(path = "{id}")
    public ResponseEntity getOneUser(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(userService.getOneUser(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }


}
