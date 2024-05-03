package com.example.lesson7.controller;

import com.example.lesson7.dto.UserDto;
import com.example.lesson7.entity.User;
import com.example.lesson7.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(userRepository.findAll());
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        User user= new User();
        user.setName(userDto.getName());
        user.setAge(userDto.getAge());
        return ResponseEntity.ok().body(userRepository.save(user));
    }
}
