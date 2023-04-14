package com.example.ecommerce.Services;

import com.example.ecommerce.Models.Product;
import com.example.ecommerce.Models.Sold;
import com.example.ecommerce.Models.tempCart;
import com.example.ecommerce.Repositories.ProductRepository;
import com.example.ecommerce.Repositories.SoldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SoldService {
    private final SoldRepository soldRepository;
    private final ProductRepository productRepository;

    public List<Sold> getAllSoldItems(){return soldRepository.findAll();}

    public Sold createSold(tempCart cart){
        Optional<Product> product = productRepository.findByName(cart.getName());
        if(product.isEmpty()) throw new Error("Could not find product.");

        Product currentProduct = product.get();

        List<Sold> soldArray = soldRepository.findAll();
        for(Sold object: soldArray){
            if(object.getProduct() == currentProduct) throw new Error("Object already exists.");
        }

        Sold sold = new Sold();
        sold.setProduct(currentProduct);
        sold.setTotalAmountBought(cart.getQuantity());

        soldRepository.save(sold);
        return sold;
    }

    public Sold updateSold(tempCart cart){
        Optional<Product> product = productRepository.findByName(cart.getName());
        if(product.isEmpty()) throw new Error("Could not find product.");

        Product currentProduct = product.get();

        Optional<Sold> sold = soldRepository.findByProduct(currentProduct);
        if(sold.isEmpty()) throw new Error("Product has not been checked out.");

        Sold toUpdateItem = sold.get();
        toUpdateItem.setTotalAmountBought(cart.getQuantity() + toUpdateItem.getTotalAmountBought());
        soldRepository.save(toUpdateItem);
        return toUpdateItem;
    }
}
