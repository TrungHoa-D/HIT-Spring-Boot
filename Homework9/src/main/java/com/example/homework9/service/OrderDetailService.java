package com.example.homework9.service;

import com.example.homework9.dto.OrderDetailDto;
import com.example.homework9.entity.Order;
import com.example.homework9.entity.OrderDetail;
import com.example.homework9.entity.Product;
import com.example.homework9.repository.OrderDetailRepository;
import com.example.homework9.repository.ProductRepository;
import com.example.homework9.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderDetail create(OrderDetailDto orderDetailDto) throws Exception {
        OrderDetail orderDetail= new OrderDetail();
        Order order= orderRepository.findById(orderDetailDto.getOrderId()).orElseThrow(() -> {
            throw  new RuntimeException();
        });
        Product product= productRepository.findById(orderDetailDto.getProductOrderId()).orElseThrow(() -> {
            throw  new RuntimeException();
        });
        orderDetail.setOrder(order);
        orderDetail.setProductOrder(product);
        return orderDetailRepository.save(orderDetail);
    }

    public List<OrderDetail> reads() {
        return orderDetailRepository.findAll();
    }

    public OrderDetail read(long id) throws Exception {
        return orderDetailRepository.findById(id).orElseThrow(() ->{
            return  new Exception("Not found");
        });
    }

    public OrderDetail update(long id,OrderDetailDto orderDetailDto) throws Exception {
        Optional<OrderDetail> orderDetail = Optional.ofNullable(orderDetailRepository.findById(id).orElseThrow(() -> {
            return new Exception("Not found");
        }));
        Order order= orderRepository.findById(orderDetailDto.getOrderId()).orElseThrow(() -> {
            throw  new RuntimeException();
        });
        Product product= productRepository.findById(orderDetailDto.getProductOrderId()).orElseThrow(() -> {
            throw  new RuntimeException();
        });
        orderDetail.get().setOrder(order);
        orderDetail.get().setProductOrder(product);
        return orderDetailRepository.save(orderDetail.get());
    }

    public void delete(long id) throws Exception {
        Optional<OrderDetail> orderDetail = Optional.ofNullable(orderDetailRepository.findById(id).orElseThrow(() -> {
            return new Exception();
        }));
        orderDetailRepository.deleteById(id);
    }
}
