package com.example.lesson7.dto;

import com.example.lesson7.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdentityCardDto {
    private String code;
    private int userId;
}
