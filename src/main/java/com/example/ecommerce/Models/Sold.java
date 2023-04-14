package com.example.ecommerce.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "sold")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Sold {
    @Id
    @SequenceGenerator(
            name = "sold_sequence",
            sequenceName = "sold_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sold_sequence"
    )
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    private Double totalAmountBought;

    public Sold(){}
    public Sold(Long id, Product product, Double totalAmountBought) {
        this.id = id;
        this.product = product;
        this.totalAmountBought = totalAmountBought;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getTotalAmountBought() {
        return totalAmountBought;
    }

    public void setTotalAmountBought(Double totalAmountBought) {
        this.totalAmountBought = totalAmountBought;
    }
}
