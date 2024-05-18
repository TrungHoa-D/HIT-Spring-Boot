package com.example.homework9.service;

import com.example.homework9.dto.CartDto;
import com.example.homework9.entity.Cart;
import com.example.homework9.entity.Product;
import com.example.homework9.entity.User;
import com.example.homework9.repository.CartRepository;
import com.example.homework9.repository.ProductRepository;
import com.example.homework9.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public Cart create(CartDto cartDto) throws Exception {
        Cart cart= new Cart();
        User user= userRepository.findById(cartDto.getUserId()).orElseThrow(() -> {throw new RuntimeException();});
        Product product= productRepository.findById(cartDto.getProductId()).orElseThrow(() -> {
            throw  new RuntimeException();
        });
        cart.setUser(user);
        cart.setProduct(product);
        return cartRepository.save(cart);
    }

    public List<Cart> reads() {
        return cartRepository.findAll();
    }

    public Cart read(long id) throws Exception {
        return cartRepository.findById(id).orElseThrow(() ->{
            return  new Exception("Not found");
        });
    }

    public Cart update(long id,CartDto cartDto) throws Exception {
        Optional<Cart> cart = Optional.ofNullable(cartRepository.findById(id).orElseThrow(() -> {
            return new Exception("Not found");
        }));
        User user= userRepository.findById(cartDto.getUserId()).orElseThrow(() -> {throw new RuntimeException();});
        Product product= productRepository.findById(cartDto.getProductId()).orElseThrow(() -> {
            throw  new RuntimeException();
        });
        cart.get().setUser(user);
        cart.get().setProduct(product);
        return cartRepository.save(cart.get());
    }

    public void delete(long id) throws Exception {
        Optional<Cart> cart = Optional.ofNullable(cartRepository.findById(id).orElseThrow(() -> {
            return new Exception();
        }));
        cartRepository.deleteById(id);
    }
}
