package com.example.homework9.controller;

import com.example.homework9.dto.CartDto;
import com.example.homework9.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    //Add Product to Cart
    @PostMapping("/cart")
    public ResponseEntity<?> create(@RequestBody CartDto cartDto) throws Exception {
        return ResponseEntity.ok().body(cartService.create(cartDto));
    }

    @GetMapping("/cart")
    public ResponseEntity<?> reads() {
        return ResponseEntity.ok().body(cartService.reads());
    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<?> read(@PathVariable long id) throws Exception {
        return ResponseEntity.ok().body(cartService.read(id));
    }

    @PutMapping("/cart/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody CartDto cartDto) throws Exception {
        return ResponseEntity.ok().body(cartService.update(id,cartDto));
    }

    @DeleteMapping("cart/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) throws Exception {
        cartService.delete(id);
        return ResponseEntity.ok().build();
    }
}
