package com.example.homework9.controller;

import com.example.homework9.dto.OrderDto;
import com.example.homework9.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OderController {
    private final OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<?> create(@RequestBody OrderDto orderDto) throws Exception {
        return ResponseEntity.ok().body(orderService.create(orderDto));
    }

    @GetMapping("/order")
    public ResponseEntity<?> reads() {
        return ResponseEntity.ok().body(orderService.reads());
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<?> read(@PathVariable long id) throws Exception {
        return ResponseEntity.ok().body(orderService.read(id));
    }

    @GetMapping("/order/user/{id}")
    public ResponseEntity<?> findByUser(@PathVariable long id) throws Exception {
        return ResponseEntity.ok().body(orderService.findByUser(id));
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody OrderDto orderDto) throws Exception {
        return ResponseEntity.ok().body(orderService.update(id,orderDto));
    }

    @DeleteMapping("order/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) throws Exception {
        orderService.delete(id);
        return ResponseEntity.ok().build();
    }
}
