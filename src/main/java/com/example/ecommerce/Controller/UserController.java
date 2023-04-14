package com.example.ecommerce.Controller;

import com.example.ecommerce.Controller.Auth.AuthenticationRequest;
import com.example.ecommerce.Controller.Auth.AuthenticationResponse;
import com.example.ecommerce.Controller.Auth.AuthenticationService;
import com.example.ecommerce.Controller.Auth.RegisterRequest;
import com.example.ecommerce.Models.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ecommerce.Services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;


    @GetMapping("/users")
    public List<UserModel> getAllUsers(){
        return userService.getAllUsers();
    }


    @GetMapping("/user/{username}")
    public UserModel getUser(@PathVariable String username){return userService.getUser(username);}

    @PutMapping("/user")
    public UserModel updateUser(@RequestBody UserModel userModel){return userService.updateUser(userModel);}

    @DeleteMapping("/user/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username){
        userService.deleteUser(username);
        return ResponseEntity.ok().body("Deleted user " + username);
    }


    //    Auth
    @PostMapping("/auth/register")
    public ResponseEntity<AuthenticationResponse>saveUser(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/auth/authenticate")
    public ResponseEntity<AuthenticationResponse>saveUser(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
