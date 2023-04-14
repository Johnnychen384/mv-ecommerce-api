package com.example.ecommerce.Repositories;

import com.example.ecommerce.Models.Product;
import com.example.ecommerce.Models.Sold;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SoldRepository extends JpaRepository<Sold, Long> {
    Optional<Sold> findByProduct(Product product);
}
