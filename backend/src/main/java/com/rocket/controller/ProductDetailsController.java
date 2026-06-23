package com.rocket.controller;

import com.rocket.entity.ProductDetails;
import com.rocket.service.ProductDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductDetailsController {
    
    @Autowired
    private ProductDetailsService productDetailsService;
    
    // Add a new product
    @PostMapping
    public ResponseEntity<ProductDetails> addProduct(@RequestBody ProductDetails productDetails) {
        ProductDetails savedProduct = productDetailsService.addProduct(productDetails);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }
    
    // Update a product
    @PutMapping("/{id}")
    public ResponseEntity<ProductDetails> updateProduct(@PathVariable Integer id, @RequestBody ProductDetails productDetails) {
        ProductDetails updatedProduct = productDetailsService.updateProduct(id, productDetails);
        if (updatedProduct != null) {
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // Delete a product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        boolean deleted = productDetailsService.deleteProduct(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductDetails> getProductById(@PathVariable Integer id) {
        Optional<ProductDetails> product = productDetailsService.getProductById(id);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // Get all products
    @GetMapping
    public ResponseEntity<List<ProductDetails>> getAllProducts() {
        List<ProductDetails> products = productDetailsService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    
    // Get product by name
    @GetMapping("/name/{productName}")
    public ResponseEntity<ProductDetails> getProductByName(@PathVariable String productName) {
        Optional<ProductDetails> product = productDetailsService.getProductByName(productName);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
