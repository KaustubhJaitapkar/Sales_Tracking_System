package com.rocket.repository;

import com.rocket.entity.Lead;
import com.rocket.entity.LeadPriority;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer> {

    // Product-wise leads
    List<Lead> findByProductProductId(Integer productId);

    // Source-wise leads
    List<Lead> findBySourceSourceId(Integer sourceId);

    // Status-wise leads
    List<Lead> findByStatusStatusId(Integer statusId);

    // Assigned employee leads
    List<Lead> findByAssignedToId(Integer employeeId);

    // Leads created by employee
    List<Lead> findByCreatedById(Integer employeeId);

    // Customer-wise leads
    List<Lead> findByCustomerCustomerId(Integer customerId);

    // Priority-wise leads
    List<Lead> findByPriority(LeadPriority priority);

    // Value-based filtering
    List<Lead> findByValueGreaterThan(Integer value);

    List<Lead> findByValueLessThan(Integer value);

    // Unassigned leads
    List<Lead> findByAssignedToIsNull();

    // Assigned leads
    List<Lead> findByAssignedToIsNotNull();

    // Status + Priority
    List<Lead> findByStatusStatusIdAndPriority(
            Integer statusId,
            LeadPriority priority
    );
}