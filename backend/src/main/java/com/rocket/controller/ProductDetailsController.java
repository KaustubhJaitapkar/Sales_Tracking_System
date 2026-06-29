package com.rocket.controller;

import com.rocket.dto.ProductDetailsDTO;
import com.rocket.service.IProductDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductDetailsController {
    
    private final IProductDetailsService productDetailsService;
    
    // Add a new product
    @PostMapping
    public ResponseEntity<ProductDetailsDTO> addProduct(@RequestBody ProductDetailsDTO productDetailsDTO) {
        ProductDetailsDTO savedProduct = productDetailsService.addProduct(productDetailsDTO);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }
    
    // Update a product
    @PutMapping("/{id}")
    public ResponseEntity<ProductDetailsDTO> updateProduct(@PathVariable Integer id, @RequestBody ProductDetailsDTO productDetails) {
        ProductDetailsDTO updatedProduct = productDetailsService.updateProduct(id, productDetails);
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
    public ResponseEntity<ProductDetailsDTO> getProductById(@PathVariable Integer id) {
        Optional<ProductDetailsDTO> product = productDetailsService.getProductById(id);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // Get all products
    @GetMapping
    public ResponseEntity<List<ProductDetailsDTO>> getAllProducts() {
        List<ProductDetailsDTO> products = productDetailsService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    
    // Get product by name
    @GetMapping("/name/{productName}")
    public ResponseEntity<List<ProductDetailsDTO>> getProductByName(@PathVariable String productName) {
        List<ProductDetailsDTO> products = productDetailsService.getProductByName(productName);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
