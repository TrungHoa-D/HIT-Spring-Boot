package com.example.homework9.service;

import com.example.homework9.dto.UserDto;
import com.example.homework9.entity.User;
import com.example.homework9.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User create(UserDto userDto) {
        User user= new User();
        user.setName(userDto.getName());
        return userRepository.save(user);
    }

    public List<User> reads() {
        return userRepository.findAll();
    }

    public User read(long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() ->{
            return  new Exception("Not found");
        });
    }

    public User update(long id,UserDto userDto) throws Exception {
        Optional<User> user = Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> {
            return new Exception("Not found");
        }));
        user.get().setName(userDto.getName());
        return userRepository.save(user.get());
    }

    public void delete(long id) throws Exception {
        Optional<User> user = Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> {
            return new Exception();
        }));
        userRepository.deleteById(id);
    }
}
