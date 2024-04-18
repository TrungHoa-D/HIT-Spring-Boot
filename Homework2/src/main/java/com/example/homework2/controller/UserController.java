package com.example.homework2.controller;

import com.example.homework2.exception.AlreadyExistException;
import com.example.homework2.exception.PasswordNotMatchException;
import com.example.homework2.model.Account;
import com.example.homework2.Homework2Application;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private List<Account> accountList = new ArrayList<>();

    @PostConstruct
    public void init() {
        accountList.add(new Account("trunghoa2k4@gmail.com", "123456", "123456"));
        accountList.add(new Account("trunghoa2k4@gmail.com", "88888888", "88888888"));
        accountList.add(new Account("trunghoa2k4@gmail.com", "00000000", "00000000"));
    }
    @GetMapping("/login")
    public String login(Model model) {
        return "/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Account account, Model model) {
        boolean exist = false;
        boolean same = true;
        if(!account.getRepeat().equals(account.getPassword())) same = false;
        for (Account a : accountList) {
            if (a.getEmail().equals(account.getEmail()) && a.getPassword().equals(account.getPassword())) exist = true;
        }
        if (exist) {
            model.addAttribute("respone", "Account already exists!");
            model.addAttribute("color", "red");
        }
        else if (!same) {
            model.addAttribute("respone", "Password does not match!");
            model.addAttribute("color", "red");
        } else {
            accountList.add(account);
            model.addAttribute("respone", "Sign up success!");
            model.addAttribute("color", "green");
        }
        return "/login";
    }

    @RequestMapping("/wellcome")
    public String wellcome(@ModelAttribute Account account, Model model) {
        boolean exist = false;
        for (Account a : accountList) {
            if (a.getEmail().equals(account.getEmail()) && a.getPassword().equals(account.getPassword())) exist = true;
        }
        if (exist) {
            model.addAttribute("list", accountList);
            return "/wellcome";
        } else {
            model.addAttribute("back", "Wrong email or password!");
            return "/login";
        }
    }

    @GetMapping("/acc/getAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(accountList);
    }
    @PostMapping("/acc/create/{acc}")
    public ResponseEntity<?> createAcc(@RequestBody Account account) throws AlreadyExistException, PasswordNotMatchException {
        boolean exist = false;
        boolean same = true;
        if(!account.getRepeat().equals(account.getPassword())) same = false;
        for (Account a : accountList) {
            if (a.getEmail().equals(account.getEmail()) && a.getPassword().equals(account.getPassword())) exist = true;
        }
        if (exist) {
            throw new AlreadyExistException("Tài khoản đã tồn tại");
        }
        else if (!same) {
            throw new PasswordNotMatchException("Mật khẩu nhập lại không khớp!");
        } else {
            accountList.add(account);
        }
        return ResponseEntity.ok().body(accountList);
    }
    @GetMapping("/acc/getOne")
    public ResponseEntity<?> getUser(@RequestParam int id) {
        Account u = accountList.get(id);
        if(accountList.contains(u))
            return ResponseEntity.ok().body(u);
        throw  new IndexOutOfBoundsException("Không tìm thấy id "+ id);
    }
    @GetMapping("/acc/getAll/{id}")
    public ResponseEntity<?> getAcc(@PathVariable int id) {
        Account u = accountList.get(id);
        if(accountList.contains(u))
            return ResponseEntity.ok().body(u);
        throw  new IndexOutOfBoundsException("Không tìm thấy id "+ id);
    }
    @DeleteMapping("/acc/deleteAcc/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        Account u = accountList.get(id);
        if(accountList.contains(u)){
            accountList.remove(id);
            return ResponseEntity.ok().body(accountList);
        }
        throw  new IndexOutOfBoundsException("Không tìm thấy id "+ id);
    }
}
