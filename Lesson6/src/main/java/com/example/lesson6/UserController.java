package com.example.lesson6;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @GetMapping("/user")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(userRepository.findAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> findByID(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        User userCreate= userRepository.save(user);
        return ResponseEntity.ok().body(userRepository.findAll());
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        userRepository.deleteById(id);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUser (@PathVariable int id, @RequestBody User user) {
        Optional<User> curUser = userRepository.findById(id);
        if (user.getUsername()!=null)
        curUser.get().setUsername(user.getUsername());
        if (user.getPassword()!=null)
        curUser.get().setPassword(user.getPassword());
        userRepository.save(curUser.get());
        return ResponseEntity.ok().body(curUser);
    }
}
