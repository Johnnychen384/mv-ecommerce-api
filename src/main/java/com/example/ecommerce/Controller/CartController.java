package com.example.ecommerce.Controller;

import com.example.ecommerce.Models.Cart;
import com.example.ecommerce.Models.tempCart;
import com.example.ecommerce.Services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/allCarts")
    public List<Cart> getAllCarts(){
        return cartService.getAllCarts();
    }

    @GetMapping("/allUserCarts/{username}")
    public List<Cart> getAllUserCarts(@PathVariable String username){
        return cartService.getUserCarts(username);
    }

    @PostMapping("/cart")
    public Cart addCart(@RequestBody tempCart cart){
        return cartService.createCart(cart);
    }

    @PutMapping("/cart")
    public Cart updateCart(@RequestBody tempCart cart){
        return cartService.updateToCart(cart);
    }


    @DeleteMapping("/cart")
    public String deletedCart(@RequestParam String username, @RequestParam String name){
        return cartService.deleteCart(username, name);
    }

    @DeleteMapping("/allCarts")
    public String deletedCart(@RequestParam String username){
        return cartService.deleteAllCart(username);
    }
}
