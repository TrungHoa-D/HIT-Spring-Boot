package com.example.lesson9.controller;

import com.example.lesson9.dto.AccountDto;
import com.example.lesson9.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/account")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(accountService.getAll());
    }

    @PostMapping("/account")
    public ResponseEntity<?> create(@RequestBody AccountDto accountDto) throws IOException {
        return ResponseEntity.ok().body(accountService.create(accountDto));
    }
}
