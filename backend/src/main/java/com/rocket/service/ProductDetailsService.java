package com.rocket.service;

import com.rocket.entity.ProductDetails;
import com.rocket.repository.ProductDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductDetailsService {
    
    @Autowired
    private ProductDetailsRepository productDetailsRepository;
    
    // Add a new product
    public ProductDetails addProduct(ProductDetails productDetails) {
        return productDetailsRepository.save(productDetails);
    }
    
    // Update a product
    public ProductDetails updateProduct(Integer productId, ProductDetails productDetails) {
        Optional<ProductDetails> optionalProduct = productDetailsRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            ProductDetails product = optionalProduct.get();
            if (productDetails.getProductName() != null) {
                product.setProductName(productDetails.getProductName());
            }
            if (productDetails.getProductType() != null) {
                product.setProductType(productDetails.getProductType());
            }
            if (productDetails.getProductStatus() != null) {
                product.setProductStatus(productDetails.getProductStatus());
            }
            if (productDetails.getDescription() != null) {
                product.setDescription(productDetails.getDescription());
            }
            return productDetailsRepository.save(product);
        }
        return null;
    }
    
    // Delete a product
    public boolean deleteProduct(Integer productId) {
        if (productDetailsRepository.existsById(productId)) {
            productDetailsRepository.deleteById(productId);
            return true;
        }
        return false;
    }
    
    // Get product by ID
    public Optional<ProductDetails> getProductById(Integer productId) {
        return productDetailsRepository.findById(productId);
    }
    
    // Get all products
    public List<ProductDetails> getAllProducts() {
        return productDetailsRepository.findAll();
    }
    
    // Get product by name
    public Optional<ProductDetails> getProductByName(String productName) {
        return productDetailsRepository.findByProductName(productName);
    }
}
