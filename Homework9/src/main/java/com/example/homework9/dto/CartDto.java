package com.example.homework9.dto;

import com.example.homework9.entity.Product;
import com.example.homework9.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private long userId;
    private long productId;
}
