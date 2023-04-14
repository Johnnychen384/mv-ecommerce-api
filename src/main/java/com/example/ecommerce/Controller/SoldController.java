package com.example.ecommerce.Controller;

import com.example.ecommerce.Models.Sold;
import com.example.ecommerce.Models.tempCart;
import com.example.ecommerce.Services.SoldService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sold")
@RequiredArgsConstructor
public class SoldController {
    private final SoldService soldService;

    @GetMapping("/sold")
    public List<Sold> getAllSold(){return soldService.getAllSoldItems();}

    @PostMapping("/sold")
    public Sold createSold(@RequestBody tempCart cart){
        return soldService.createSold(cart);
    }

    @PutMapping("/sold")
    public Sold updateSold(@RequestBody tempCart cart){
        return soldService.updateSold(cart);
    }
}
