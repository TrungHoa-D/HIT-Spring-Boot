package com.example.lesson7.dto;

import com.example.lesson7.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeadlineDto {
    private String name;
    private User user;
}
