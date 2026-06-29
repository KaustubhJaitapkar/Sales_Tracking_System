package com.rocket.repository;

import com.rocket.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    
    @Query("SELECT c FROM Customer c WHERE c.email = ?1 AND c.isActive = true")
    Optional<Customer> findByEmail(String email);
    
    @Query("SELECT c FROM Customer c WHERE c.phoneNo = ?1 AND c.isActive = true")
    Optional<Customer> findByPhoneNo(String phoneNo);
    
    @Query("SELECT c FROM Customer c WHERE c.isActive = true")
    List<Customer> findAllActive();
    
    @Query("SELECT c FROM Customer c WHERE c.customerId = ?1 AND c.isActive = true")
    Optional<Customer> findByIdActive(Integer customerId);
}
