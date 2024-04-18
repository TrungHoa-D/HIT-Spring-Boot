package com.example.lesson5.controller;

import com.example.lesson5.exception.NotFoundException;
import com.example.lesson5.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.NotActiveException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class UserController {
    private List<User> users;

    @PostConstruct
    public void init() {
        users = IntStream.range(1, 10)
                .mapToObj(i -> new User(i,"username"+i,"pass"+i))
                .collect(Collectors.toList());
    }
    @GetMapping("/users/getAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(users);
    }
    @GetMapping("/users/getAll/{id}")
    public ResponseEntity<?> getUser(@PathVariable int id) {
        User u = users.get(id);
        if(users.contains(u))
            return ResponseEntity.ok().body(u);
        throw  new NotFoundException("Khong tim thay id "+ id);
    }
    @PostMapping("users/createUser")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        users.add(user);
        return ResponseEntity.ok().body(users);
    }
    @DeleteMapping("/users/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        users.remove(id);
        return ResponseEntity.ok().body(users);
    }
}
