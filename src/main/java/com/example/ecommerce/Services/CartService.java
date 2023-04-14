package com.example.ecommerce.Services;

import com.example.ecommerce.Models.Cart;
import com.example.ecommerce.Models.Product;
import com.example.ecommerce.Models.UserModel;
import com.example.ecommerce.Models.tempCart;
import com.example.ecommerce.Repositories.CartRepository;
import com.example.ecommerce.Repositories.ProductRepository;
import com.example.ecommerce.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public List<Cart> getAllCarts(){
        return cartRepository.findAll();
    }

    public List<Cart> getUserCarts(String username){
        Optional<UserModel> user = userRepository.findByUsername(username);
        if(user.isEmpty()) throw new Error("Could not find user.");

        UserModel currentUser = user.get();
        List<Cart> cartsArray = new ArrayList<>();

        for(Cart object: cartRepository.findAll()){
            if(object.getUserModel() == currentUser) cartsArray.add(object);
        }


        return cartsArray;
    }

    public Cart createCart(tempCart cart){
        Optional<UserModel> user = userRepository.findByUsername(cart.getUsername());
        if(user.isEmpty()) throw new Error("Could not find user.");

        Optional<Product> product = productRepository.findByName(cart.getName());
        if(product.isEmpty()) throw new Error("Could not find product.");

        UserModel currentUser = user.get();
        Product currentProduct = product.get();

        List<Cart> cartsArray = cartRepository.findAll();
        for(Cart object: cartsArray){
            if(object.getProduct() == currentProduct && object.getUserModel() == currentUser) throw new Error("Cart already exists.");
        }

        Cart newCart = new Cart();
        newCart.setUserModel(currentUser);
        newCart.setProduct(currentProduct);
        newCart.setQuantity((Double) cart.getQuantity());
        newCart.setTotal(currentProduct.getPrice() * cart.getQuantity());
//        currentUser.getCart().add(newCart);
//        userRepository.save(currentUser);
        cartRepository.save(newCart);

        return newCart;
    }

    public Cart updateToCart(tempCart cart){
        Optional<UserModel> user = userRepository.findByUsername(cart.getUsername());
        if(user.isEmpty()) throw new Error("Could not find user.");

        Optional<Product> product = productRepository.findByName(cart.getName());
        if(product.isEmpty()) throw new Error("Could not find product.");

        UserModel currentUser = user.get();
        Product currentProduct = product.get();

        List<Cart> cartsArray = cartRepository.findAll();
        Cart newCart = null;
        for(Cart object: cartsArray){
            if(object.getUserModel() == currentUser && object.getProduct() == currentProduct) {
                newCart = object;
            }
        }

        if(newCart == null) throw new Error("Cart cant be found.");

        newCart.setQuantity(cart.getQuantity());
        newCart.setTotal(currentProduct.getPrice() * cart.getQuantity());
        cartRepository.save(newCart);

        return newCart;




    }


    public String deleteCart(String username, String name){
        Optional<UserModel> user = userRepository.findByUsername(username);
        if(user.isEmpty()) throw new Error("Could not find user.");

        Optional<Product> product = productRepository.findByName(name);
        if(product.isEmpty()) throw new Error("Could not find product.");

        UserModel currentUser = user.get();
        Product currentProduct = product.get();

        List<Cart> cartsArray = cartRepository.findAll();
        Cart newCart = null;
        for(Cart object: cartsArray){
            if(object.getUserModel() == currentUser && object.getProduct() == currentProduct) {
                newCart = object;
            }
        }

        if(newCart == null) return "Could not find cart.";

        cartRepository.deleteById(newCart.getId());
        return "Deleted Successfully";
    }

    public String deleteAllCart(String username){
        Optional<UserModel> user = userRepository.findByUsername(username);
        if(user.isEmpty()) throw new Error("Could not find user.");

        UserModel currentUser = user.get();

        List<Cart> cartsArray = cartRepository.findAll();
        for(Cart object: cartsArray){
            if(object.getUserModel() == currentUser) {
                cartRepository.deleteById(object.getId());
            }
        }

        return "Deleted Successfully";
    }
}
