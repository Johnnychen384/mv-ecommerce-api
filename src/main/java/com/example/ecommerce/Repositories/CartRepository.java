package com.example.ecommerce.Repositories;

import com.example.ecommerce.Models.Cart;
import com.example.ecommerce.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserModel(UserModel userModel);
}
