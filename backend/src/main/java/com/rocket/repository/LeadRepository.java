package com.rocket.repository;

import com.rocket.entity.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer> {
    
    List<Lead> findByProductProductId(Integer productId);
    
    List<Lead> findBySourceSourceId(Integer sourceId);
    
    List<Lead> findByStatusStatusId(Integer statusId);
    
    List<Lead> findByAssignedToId(Integer employeeId);
    
    List<Lead> findByCreatedById(Integer employeeId);
    
    List<Lead> findByCustomerCustomerId(Integer customerId);
}
