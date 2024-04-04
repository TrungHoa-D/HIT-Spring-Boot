package com.example.homework2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Homework2Application {
    public static List<Account> accounts= new ArrayList<>();
    public static void main(String[] args) {
        accounts.add(new Account("trunghoa2k4@gmail.com","123456"));
        accounts.add(new Account("trunghoa2k4@gmail.com","88888888"));
        accounts.add(new Account("trunghoa2k4@gmail.com","00000000"));
        SpringApplication.run(Homework2Application.class, args);
    }

}
