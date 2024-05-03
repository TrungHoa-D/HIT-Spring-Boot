package com.example.lesson7.controller;

import com.example.lesson7.dto.IdentityCardDto;
import com.example.lesson7.dto.UserDto;
import com.example.lesson7.entity.IdentityCard;
import com.example.lesson7.entity.User;
import com.example.lesson7.repository.IdentityCardRepository;
import com.example.lesson7.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class IdentityCardController {
    @Autowired
    private IdentityCardRepository identityCardRepository;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/ics")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(identityCardRepository.findAll());
    }

    @PostMapping("/ics")
    public ResponseEntity<?> createIdentityCard(@RequestBody IdentityCardDto identityCardDto) {
        IdentityCard ic= new IdentityCard();
        ic.setCode(identityCardDto.getCode());
        User userFind = userRepository.findById(identityCardDto.getUserId()).orElseThrow(() ->{
            throw new RuntimeException();
        });
        ic.setUser(userFind);
        return ResponseEntity.ok().body(identityCardRepository.save(ic));
    }
}
