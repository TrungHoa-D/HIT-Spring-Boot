package com.example.homework2.controller;

import com.example.homework2.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @RequestMapping("/login")
    public String login(Model model){
        return "login";
    }
    @RequestMapping(value = "/wrong")
    public String wrong(@ModelAttribute Account account, Model model){
        model.addAttribute("acc", account);
        if(account.getEmail().equals("trunghoa2k4@gmail.com") && account.getPassword().equals("123456"))
            return "redirect:wellcome";
        else
            return "wrong";
    }
    @RequestMapping("/wellcome")
    public String wellcome(Model model){
        String name="Hòa";
        model.addAttribute("name", name);
        return "wellcome";
    }
}
