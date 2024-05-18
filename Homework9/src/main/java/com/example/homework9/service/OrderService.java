package com.example.homework9.service;

import com.example.homework9.dto.OrderDto;
import com.example.homework9.entity.Order;
import com.example.homework9.entity.User;
import com.example.homework9.repository.OrderRepository;
import com.example.homework9.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public Order create(OrderDto orderDto) throws Exception {
        Order order= new Order();
        User user= userRepository.findById(orderDto.getUserOrderId()).orElseThrow(() -> {throw new RuntimeException();});
        order.setUserOrder(user);
        return orderRepository.save(order);
    }

    public List<Order> reads() {
        return orderRepository.findAll();
    }

    public Order read(long id) throws Exception {
        return orderRepository.findById(id).orElseThrow(() ->{
            return  new Exception("Not found");
        });
    }

    public Order update(long id,OrderDto orderDto) throws Exception {
        Optional<Order> order = Optional.ofNullable(orderRepository.findById(id).orElseThrow(() -> {
            return new Exception("Not found");
        }));
        User user= userRepository.findById(orderDto.getUserOrderId()).orElseThrow(() -> {throw new RuntimeException();});
        order.get().setUserOrder(user);
        return orderRepository.save(order.get());
    }

    public void delete(long id) throws Exception {
        Optional<Order> order = Optional.ofNullable(orderRepository.findById(id).orElseThrow(() -> {
            return new Exception("Not found");
        }));
        orderRepository.deleteById(id);
    }

    public List<Order> findByUser(long id) throws Exception{
        return orderRepository.findOrdersByUserOrder(id);
    }
}
