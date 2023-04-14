package com.example.ecommerce.Controller;

import com.example.ecommerce.Models.Product;
import com.example.ecommerce.Services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/{name}")
    public Product getProduct(@PathVariable String name){
        return productService.getProduct(name);
    }

    @PostMapping("/")
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }
}
