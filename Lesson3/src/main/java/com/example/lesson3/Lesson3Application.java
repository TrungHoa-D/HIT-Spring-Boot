package com.example.lesson3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Lesson3Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Lesson3Application.class, args);
        Phone phone = context.getBean(Phone.class);
        System.out.println(phone.getName());
    }

}
