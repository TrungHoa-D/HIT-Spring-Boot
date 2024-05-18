package com.example.homework9.dto;

import com.example.homework9.entity.Order;
import com.example.homework9.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto {
    private long orderId;
    private long productOrderId;
}
