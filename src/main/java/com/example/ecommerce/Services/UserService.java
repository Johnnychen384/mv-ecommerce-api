package com.example.ecommerce.Services;

import com.example.ecommerce.Models.UserModel;
import com.example.ecommerce.Repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserModel> getAllUsers(){
        return userRepository.findAll();
    }

    public UserModel getUser(String username){
        Optional<UserModel> user = userRepository.findByUsername(username);
        if(user.isEmpty()) throw new Error("Username not found");

        return user.get();
    }


    public UserModel updateUser(@RequestBody UserModel partialUser){
        Optional<UserModel> user = userRepository.findByUsername(partialUser.getUsername());
        if(user.isEmpty()) throw new Error("Could not find user.");

        UserModel currentUser = user.get();

        if(!currentUser.getUsername().equals(partialUser.getUsername())) currentUser.setUsername(partialUser.getUsername());
        if(!currentUser.getEmail().equals(partialUser.getEmail())) currentUser.setEmail(partialUser.getEmail());
        if(!currentUser.getPassword().equals(partialUser.getPassword())) currentUser.setPassword(passwordEncoder.encode(partialUser.getPassword()));
        if(!currentUser.getAddress().equals(partialUser.getAddress())) currentUser.setAddress(partialUser.getAddress());
        if(!currentUser.getCreditcard().equals(partialUser.getCreditcard())) currentUser.setCreditcard(partialUser.getCreditcard());

        userRepository.save(currentUser);
        return currentUser;
    }

    public String deleteUser(@PathVariable String username){
        Optional<UserModel> user = userRepository.findByUsername(username);
        if(user.isEmpty()) throw new Error("Could not find user.");

        UserModel currentUser = user.get();
        userRepository.deleteById(currentUser.getId());

        return "Removed User " + username;
    }
}
