package com.library.controller;

import com.library.model.User;
import com.library.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello world");
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        Optional<User> byId = userService.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User player) {
        return ResponseEntity.ok(userService.save(player));
    }
    @PutMapping
    public ResponseEntity<User> update(@RequestBody User player) {
        return ResponseEntity.ok(userService.update(player));
    }

    @GetMapping("/addbook/{userId}/{bookId}")
    public ResponseEntity<User> addBook(@PathVariable Long userId, @PathVariable Long bookId){
        return ResponseEntity.ok(userService.addBook(userId,bookId));
    }

    @GetMapping("/removebook/{userId}/{bookId}")
    public ResponseEntity<User> removeBook(@PathVariable Long userId, @PathVariable Long bookId){
        return ResponseEntity.ok(userService.removeBook(userId,bookId));
    }

    @GetMapping("/findbyname/{name}")
    public ResponseEntity<List<User>> findByName(@PathVariable String name){
        Optional<List<User>> user = userService.findByName(name);
        if(user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

}
