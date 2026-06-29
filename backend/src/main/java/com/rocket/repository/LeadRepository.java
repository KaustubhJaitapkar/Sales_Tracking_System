package com.rocket.repository;

import com.rocket.entity.Lead;
import com.rocket.entity.LeadPriority;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer> {

    // Product-wise leads
    @Query("SELECT l FROM Lead l WHERE l.product.productId = ?1 AND l.isActive = true")
    List<Lead> findByProductProductId(Integer productId);

    // Source-wise leads
    @Query("SELECT l FROM Lead l WHERE l.source.sourceId = ?1 AND l.isActive = true")
    List<Lead> findBySourceSourceId(Integer sourceId);

    // Status-wise leads
    @Query("SELECT l FROM Lead l WHERE l.status.statusId = ?1 AND l.isActive = true")
    List<Lead> findByStatusStatusId(Integer statusId);

    // Assigned employee leads
    @Query("SELECT l FROM Lead l WHERE l.assignedTo.id = ?1 AND l.isActive = true")
    List<Lead> findByAssignedToId(Integer employeeId);

    // Leads created by employee
    @Query("SELECT l FROM Lead l WHERE l.createdBy.id = ?1 AND l.isActive = true")
    List<Lead> findByCreatedById(Integer employeeId);

    // Customer-wise leads
    @Query("SELECT l FROM Lead l WHERE l.customer.customerId = ?1 AND l.isActive = true")
    List<Lead> findByCustomerCustomerId(Integer customerId);

    // Priority-wise leads
    @Query("SELECT l FROM Lead l WHERE l.priority = ?1 AND l.isActive = true")
    List<Lead> findByPriority(LeadPriority priority);

    // Value-based filtering
    @Query("SELECT l FROM Lead l WHERE l.value > ?1 AND l.isActive = true")
    List<Lead> findByValueGreaterThan(Integer value);

    @Query("SELECT l FROM Lead l WHERE l.value < ?1 AND l.isActive = true")
    List<Lead> findByValueLessThan(Integer value);

    // Unassigned leads
    @Query("SELECT l FROM Lead l WHERE l.assignedTo IS NULL AND l.isActive = true")
    List<Lead> findByAssignedToIsNull();

    // Assigned leads
    @Query("SELECT l FROM Lead l WHERE l.assignedTo IS NOT NULL AND l.isActive = true")
    List<Lead> findByAssignedToIsNotNull();

    // Status + Priority
    @Query("SELECT l FROM Lead l WHERE l.status.statusId = ?1 AND l.priority = ?2 AND l.isActive = true")
    List<Lead> findByStatusStatusIdAndPriority(
            Integer statusId,
            LeadPriority priority
    );
    
    @Query("SELECT l FROM Lead l WHERE l.isActive = true")
    List<Lead> findAllActive();
    
    @Query("SELECT l FROM Lead l WHERE l.leadId = ?1 AND l.isActive = true")
    Optional<Lead> findByIdActive(Integer leadId);
}
