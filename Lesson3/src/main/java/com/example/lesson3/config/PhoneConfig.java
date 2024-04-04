package com.example.lesson3.config;

import com.example.lesson3.Phone;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PhoneConfig {
    @Bean
    public Phone get() {
        return new Phone("Samsung");
    }
}
