package com.example.homework9.service;

import com.example.homework9.dto.ProductDto;
import com.example.homework9.entity.Product;
import com.example.homework9.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product create(ProductDto productDto) {
        Product product= new Product();
        product.setName(productDto.getName());
        return productRepository.save(product);
    }

    public List<Product> reads() {
        return productRepository.findAll();
    }

    public Product read(long id) throws Exception {
        return productRepository.findById(id).orElseThrow(() ->{
            return  new Exception("Not found");
        });
    }

    public Product update(long id,ProductDto productDto) throws Exception {
        Optional<Product> product = Optional.ofNullable(productRepository.findById(id).orElseThrow(() -> {
            return new Exception("Not found");
        }));
        product.get().setName(productDto.getName());
        return productRepository.save(product.get());
    }

    public void delete(long id) throws Exception {
        Optional<Product> product = Optional.ofNullable(productRepository.findById(id).orElseThrow(() -> {
            return new Exception();
        }));
        productRepository.deleteById(id);
    }
}
