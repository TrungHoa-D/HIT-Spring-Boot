package com.example.homework9.controller;

import com.example.homework9.dto.UserDto;
import com.example.homework9.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<?> create(@RequestBody UserDto userDto) {
        return ResponseEntity.ok().body(userService.create(userDto));
    }

    @GetMapping("/user")
    public ResponseEntity<?> reads() {
        return ResponseEntity.ok().body(userService.reads());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> read(@PathVariable long id) throws Exception {
        return ResponseEntity.ok().body(userService.read(id));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody UserDto userDto) throws Exception {
        return ResponseEntity.ok().body(userService.update(id,userDto));
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) throws Exception {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
