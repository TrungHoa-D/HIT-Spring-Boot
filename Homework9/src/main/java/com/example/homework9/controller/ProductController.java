package com.example.homework9.controller;

import com.example.homework9.dto.ProductDto;
import com.example.homework9.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<?> create(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok().body(productService.create(productDto));
    }

    @GetMapping("/product")
    public ResponseEntity<?> reads() {
        return ResponseEntity.ok().body(productService.reads());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> read(@PathVariable long id) throws Exception {
        return ResponseEntity.ok().body(productService.read(id));
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody ProductDto productDto) throws Exception {
        return ResponseEntity.ok().body(productService.update(id,productDto));
    }

    @DeleteMapping("product/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) throws Exception {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }
}
