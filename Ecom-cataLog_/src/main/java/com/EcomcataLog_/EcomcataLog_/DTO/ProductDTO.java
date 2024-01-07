package com.EcomcataLog_.EcomcataLog_.DTO;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private double price;
    private Long categoryId; // Use categoryId to link the product to a category
    private String productDesc;
}
