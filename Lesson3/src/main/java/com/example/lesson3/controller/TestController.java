package com.example.lesson3.controller;

import com.example.lesson3.Phone;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {
    private List<Phone> list = new ArrayList<>();

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @PostMapping("/index")
    public ResponseEntity<Phone> addPhone(@RequestParam String name) {
        Phone phone = new Phone(name);
        list.add(phone);
        return ResponseEntity.ok().body(phone);
    }

    @GetMapping("/about")
    public String listPhone(Model model) {
        model.addAttribute("list", list);
        return "about";
    }

    @GetMapping("/data")
    public ResponseEntity<List<Phone>> data() {
        return ResponseEntity.ok().body(list);
    }
}
