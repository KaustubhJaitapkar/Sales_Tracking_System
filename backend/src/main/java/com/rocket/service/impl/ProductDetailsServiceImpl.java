package com.rocket.service.impl;

import com.rocket.dto.ProductDetailsDTO;
import com.rocket.entity.ProductDetails;
import com.rocket.mapper.EntityDTOMapper;
import com.rocket.repository.ProductDetailsRepository;
import com.rocket.service.IProductDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProductDetailsServiceImpl implements IProductDetailsService {
    
    private final ProductDetailsRepository productDetailsRepository;
    private final EntityDTOMapper mapper;
    
    @Override
    public ProductDetailsDTO addProduct(ProductDetailsDTO productDetailsDTO) {
        log.info("Adding new product: {}", productDetailsDTO.getProductName());
        ProductDetails productDetails = mapper.toProductDetailsEntity(productDetailsDTO);
        ProductDetails saved = productDetailsRepository.save(productDetails);
        return mapper.toProductDetailsDTO(saved);
    }
    
    @Override
    public ProductDetailsDTO updateProduct(Integer productId, ProductDetailsDTO productDetails) {
        log.info("Updating product with ID: {}", productId);
        Optional<ProductDetails> optionalProduct = productDetailsRepository.findByIdActive(productId);
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
            ProductDetails updated = productDetailsRepository.save(product);
            return mapper.toProductDetailsDTO(updated);
        }
        return null;
    }
    
    @Override
    public boolean deleteProduct(Integer productId) {
        log.info("Deleting product with ID: {}", productId);
        Optional<ProductDetails> optionalProduct = productDetailsRepository.findByIdActive(productId);
        if (optionalProduct.isPresent()) {
            ProductDetails product = optionalProduct.get();
            product.setIsActive(false);
            productDetailsRepository.save(product);
            return true;
        }
        return false;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<ProductDetailsDTO> getProductById(Integer productId) {
        log.info("Fetching product with ID: {}", productId);
        return productDetailsRepository.findByIdActive(productId)
                .map(mapper::toProductDetailsDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProductDetailsDTO> getAllProducts() {
        log.info("Fetching all products");
        return productDetailsRepository.findAllActive()
                .stream()
                .map(mapper::toProductDetailsDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProductDetailsDTO> getProductByName(String productName) {
        log.info("Fetching products by name: {}", productName);
        return productDetailsRepository.findByProductName(productName)
                .stream()
                .map(mapper::toProductDetailsDTO)
                .collect(Collectors.toList());
    }
}
