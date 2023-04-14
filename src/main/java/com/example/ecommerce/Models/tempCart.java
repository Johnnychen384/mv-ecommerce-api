package com.example.ecommerce.Models;

import lombok.*;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class tempCart {
    private String username;
    private String name;
    private Double quantity;
}
