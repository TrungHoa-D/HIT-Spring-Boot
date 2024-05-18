package com.example.homework9.controller;

import com.example.homework9.dto.OrderDetailDto;
import com.example.homework9.dto.OrderDto;
import com.example.homework9.entity.Order;
import com.example.homework9.service.OrderDetailService;
import com.example.homework9.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderDetailController {
    private final OrderDetailService orderDetailService;
    private final OrderService orderService;

    @PostMapping("/orderDetail")
    public ResponseEntity<?> create(@RequestBody OrderDetailDto orderDetailDto) throws Exception {
        return ResponseEntity.ok().body(orderDetailService.create(orderDetailDto));
    }

    @PostMapping("/orderProduct")
    public ResponseEntity<?> orderProduct(@RequestParam long userId, @RequestParam long productId) throws Exception {
        OrderDto orderDto= new OrderDto(userId);
        orderService.create(orderDto);
        List<Order> order = orderService.findByUser(orderDto.getUserOrderId());
        OrderDetailDto orderDetailDto= new OrderDetailDto(order.get(order.size()-1).getId(),productId);
        return ResponseEntity.ok().body(orderDetailService.create(orderDetailDto));
    }

    @GetMapping("/orderDetail")
    public ResponseEntity<?> reads() {
        return ResponseEntity.ok().body(orderDetailService.reads());
    }

    @GetMapping("/orderDetail/{id}")
    public ResponseEntity<?> read(@PathVariable long id) throws Exception {
        return ResponseEntity.ok().body(orderDetailService.read(id));
    }

    @PutMapping("/orderDetail/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody OrderDetailDto orderDetailDto) throws Exception {
        return ResponseEntity.ok().body(orderDetailService.update(id,orderDetailDto));
    }

    @DeleteMapping("orderDetail/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) throws Exception {
        orderDetailService.delete(id);
        return ResponseEntity.ok().build();
    }
}
