package com.EcomcataLog_.EcomcataLog_.service;

import com.EcomcataLog_.EcomcataLog_.DTO.ProductDTO;
import com.EcomcataLog_.EcomcataLog_.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getProductsByCategory(Long categoryId);
    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(Long productId);

    void addProduct(ProductDTO productDTO);

    void updateProduct(Long productId, ProductDTO productDTO);

    void deleteProduct(Long productId);

    void deleteProductsByCategory(Long categoryId);
}
