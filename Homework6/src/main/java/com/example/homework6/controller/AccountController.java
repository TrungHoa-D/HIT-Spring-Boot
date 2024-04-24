package com.example.homework6.controller;

import com.example.homework6.model.Account;
import com.example.homework6.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AccountController {
    private final AccountRepository accountRepository;
    LocalDateTime currentTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedTime = currentTime.format(formatter);
    @PostConstruct
    public void init() {
        List<Account> list= new ArrayList<>();
        list.add( new Account(1,"Hoa", "123456", "trunghoa2k4@gmail.com",formattedTime, formattedTime));
        list.add(  new Account(2,"Hoa", "88888888", "trunghoa2k4@gmail.com",formattedTime, formattedTime));
        list.add(  new Account(3,"Hoa", "00000000", "trunghoa2k4@gmail.com",formattedTime, formattedTime));
        list.forEach(account -> {
            boolean exist=false;
            for (Account a : accountRepository.findAll()) {
                if (a.getName().equals(account.getName()) && a.getPassword().equals(account.getPassword())) exist = true;
            }
            if (!exist) accountRepository.save(account);
        });
    }
    @RequestMapping("/")
    public String home(Model model) {
        return "/login";
    }
    @RequestMapping("/signup")
    public String signup(@ModelAttribute Account account, @RequestParam String repeat, Model model) {
        boolean exist = false;
        boolean same = true;
        System.out.println(account);
        System.out.println(repeat);
        if(!repeat.equals(account.getPassword())) same = false;
        for (Account a : accountRepository.findAll()) {
            if (a.getName().equals(account.getName()) && a.getPassword().equals(account.getPassword())) exist = true;
        }
        if (exist) {
            model.addAttribute("respone", "Account already exists!");
            model.addAttribute("color", "red");
        }
        else if (!same) {
            model.addAttribute("respone", "Password does not match!");
            model.addAttribute("color", "red");
        } else {
            account.setTimeCreate(formattedTime);
            account.setTimeEdit(formattedTime);
            accountRepository.save(account);
            model.addAttribute("respone", "Sign up success!");
            model.addAttribute("color", "green");
        }
        return "/login";
    }
    @RequestMapping("/wellcome")
    public String wellcome(@ModelAttribute Account account, Model model) {
        boolean exist = false;
        List<Account> list =accountRepository.findAll();
        for (Account a : list) {
            if (a.getName().equals(account.getName()) && a.getPassword().equals(account.getPassword())) exist = true;
        }
        if (exist) {
            model.addAttribute("list", list);
            return "/wellcome";
        } else {
            model.addAttribute("back", "Wrong email or password!");
            return "/login";
        }
    }
    @GetMapping("/getAcc")
    public ResponseEntity<?> getAcc() {
        return ResponseEntity.ok().body(accountRepository.findAll());
    }
    @RequestMapping("/find")
    public String find(@RequestParam String name, Model model){
        model.addAttribute("list", accountRepository.findByName(name));
        return "/wellcome";
    }
    @RequestMapping("/signout")
    public String signout() {
        return "/login";
    }
    @RequestMapping("/delete")
    public String delete(@RequestParam int id, Model model){
        accountRepository.deleteById(id);
        model.addAttribute("list", accountRepository.findAll());
        return "/wellcome";
    }
    @RequestMapping("/edit")
    public String edit(@ModelAttribute Account account, Model model){
        model.addAttribute("acc",account);
        return "/edit";
    }
    @RequestMapping("/save")
    public String save(@RequestParam int id, @RequestParam String email, @RequestParam String password, Model model) {
        Optional<Account> curAcc = accountRepository.findById(id);
        if (email!=null)
            curAcc.get().setEmail(email);
        if (password!=null)
            curAcc.get().setPassword(password);
        curAcc.get().setTimeEdit(formattedTime);
        accountRepository.save(curAcc.get());
        model.addAttribute("list", accountRepository.findAll());
        return "/wellcome";
    }
}
