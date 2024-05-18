package com.example.homework9.repository;

import com.example.homework9.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findOrdersByUserOrder(long id);
}
