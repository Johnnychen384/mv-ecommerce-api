package com.example.ecommerce.Services;

import com.example.ecommerce.Models.Product;
import com.example.ecommerce.Repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProduct(String name){
        Optional<Product> product = productRepository.findByName(name);
        if(product.isEmpty()) throw new Error("Product does not exist.");

        return product.get();
    }

    public Product createProduct(Product product){
        Optional<Product> checkProduct = productRepository.findByName(product.getName());
        if(checkProduct.isPresent()) throw new Error("Product already exists.");

        productRepository.save(product);

        return product;
    }
}
