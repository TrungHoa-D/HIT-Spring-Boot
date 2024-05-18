package com.example.lesson9.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryCofig {
    @Bean
    Cloudinary configCouldinary() {
        Map<String, String> config= new HashMap<>();
        config.put("api_key","134361596545785");
        config.put("api_secret","VoA0UNvjwn_t1U5cTPNdgiB4zhg");
        config.put("cloud_name","dajzv8hak");
        return new Cloudinary(config);
    }
}
