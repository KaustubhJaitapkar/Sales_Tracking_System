package com.rocket.repository;

import com.rocket.entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Integer> {
    
    @Query("SELECT p FROM ProductDetails p WHERE p.productName = ?1 AND p.isActive = true")
    List<ProductDetails> findByProductName(String productName);
    
    @Query("SELECT p FROM ProductDetails p WHERE p.isActive = true")
    List<ProductDetails> findAllActive();
    
    @Query("SELECT p FROM ProductDetails p WHERE p.productId = ?1 AND p.isActive = true")
    Optional<ProductDetails> findByIdActive(Integer productId);
}
