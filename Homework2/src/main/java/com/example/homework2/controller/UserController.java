package com.example.homework2.controller;

import com.example.homework2.Account;
import com.example.homework2.Homework2Application;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private List<Account> accountList= Homework2Application.accounts;
    @GetMapping("/login")
    public String login(Model model){
        return "/login";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute Account account, Model model){
        boolean exist=false;
        for(Account a : accountList) {
            if(a.getEmail().equals(account.getEmail()) && a.getPassword().equals(account.getPassword())) exist=true;
        }
        if(exist) {
            model.addAttribute("respone", "Account already exists!");
            model.addAttribute("color", "red");
            System.out.println("Account already exists!");
        }
        else {
            accountList.add(account);
            model.addAttribute("respone", "Sign up success!");
            model.addAttribute("color", "green");
            System.out.println("Sign up success!");
        }
        return ("/login");
    }
    @RequestMapping("/wellcome")
    public String wellcome(@ModelAttribute Account account, Model model){
        boolean exist=false;
        for(Account a : accountList) {
            if(a.getEmail().equals(account.getEmail()) && a.getPassword().equals(account.getPassword())) exist=true;
        }
        if(exist) {
            model.addAttribute("list",accountList);
            return "/wellcome";
        }
        else {
            model.addAttribute("back","Wrong email or password!");
            return "/login";
        }
    }
    @GetMapping("/data")
    public ResponseEntity<List<Account>> data(){
        return ResponseEntity.ok().body(accountList);
    }
}
