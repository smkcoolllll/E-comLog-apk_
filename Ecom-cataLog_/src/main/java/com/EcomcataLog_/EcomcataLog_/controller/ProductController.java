package com.EcomcataLog_.EcomcataLog_.controller;

import com.EcomcataLog_.EcomcataLog_.DTO.OrderNotificationRequest;
import com.EcomcataLog_.EcomcataLog_.DTO.ProductDTO;
import com.EcomcataLog_.EcomcataLog_.entity.Product;
import com.EcomcataLog_.EcomcataLog_.service.OrderNotificationService;
import com.EcomcataLog_.EcomcataLog_.service.ProductService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {


    @Value("${welcome.message}")
    private String message;
    @GetMapping("/print")
    public String print(){
        return message;
    }

    @Autowired
    private ProductService productService;

    @GetMapping("/category/{categoryId}")  // Use a unique path for getting products by category
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable Long categoryId) {
        List<ProductDTO> products = productService.getProductsByCategory(categoryId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId) {
        ProductDTO product = productService.getProductById(productId);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }


    @PostMapping("/save")
    public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO) {
        productService.addProduct(productDTO);
        return ResponseEntity.ok("Product added successfully");
    }

    @PutMapping("/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable Long productId, @RequestBody ProductDTO productDTO) {
        productService.updateProduct(productId, productDTO);
        return ResponseEntity.ok("Product updated successfully");
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @DeleteMapping("/deleteByCategory/{categoryId}")
    public ResponseEntity<String> deleteProductsByCategory(@PathVariable Long categoryId) {
        productService.deleteProductsByCategory(categoryId);
        return ResponseEntity.ok("Products related to the category deleted successfully");
    }

    @Autowired
    private OrderNotificationService orderNotificationService;

    @PostMapping("/send-order-notification")
    public ResponseEntity<String> sendOrderNotification(@RequestBody OrderNotificationRequest request) {
        try {
            orderNotificationService.sendOrderNotification(
                    request.getRecipientEmail(),
                    request.getProductName(),
                    request.getProductPrice()
            );
            return ResponseEntity.ok("Order notification sent successfully");
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send order notification");
        }
    }
}

