package com.example.lesson2.controller;

import com.example.lesson2.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @RequestMapping("/index")
    public String index(Model model) {
        List<User> userList = new ArrayList<>();
        userList.add(new User("Kien", 18));
        userList.add(new User("Kien", 19));
        userList.add(new User("Kien", 20));
        model.addAttribute("userList", userList);
        return "index";
    }

    @RequestMapping("/about")
    public String About(@ModelAttribute User u, Model model) {
        model.addAttribute("user", u);
        if (u.getName().length() > 1 && u.getAge() > 18)
            return "redirect:display";
        else
            return "about";
    }

    @RequestMapping("/display")
    public String display(Model model) {
        List<User> userList = new ArrayList<>();
        userList.add(new User("Kien", 18));
        userList.add(new User("Kien", 19));
        userList.add(new User("Kien", 20));
        model.addAttribute("list", userList);
        return "display";
    }
}
