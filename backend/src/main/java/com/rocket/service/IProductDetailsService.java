package com.rocket.service;

import com.rocket.dto.ProductDetailsDTO;
import java.util.List;
import java.util.Optional;

public interface IProductDetailsService {
    
    ProductDetailsDTO addProduct(ProductDetailsDTO productDetailsDTO);
    
    ProductDetailsDTO updateProduct(Integer productId, ProductDetailsDTO productDetails);
    
    boolean deleteProduct(Integer productId);
    
    Optional<ProductDetailsDTO> getProductById(Integer productId);
    
    List<ProductDetailsDTO> getAllProducts();
    
    List<ProductDetailsDTO> getProductByName(String productName);
}
