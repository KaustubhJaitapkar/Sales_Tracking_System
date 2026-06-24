package com.rocket.repository;

import com.rocket.entity.LeadAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeadAssignmentRepository extends JpaRepository<LeadAssignment, Integer> {

    // Complete assignment history of a lead
    List<LeadAssignment> findByLeadLeadIdOrderByAssignedAtDesc(Integer leadId);

    // All leads currently/historically assigned to an employee
    List<LeadAssignment> findByAssignedToIdOrderByAssignedAtDesc(Integer employeeId);

    // All assignments performed by a manager
    List<LeadAssignment> findByAssignedByIdOrderByAssignedAtDesc(Integer employeeId);

    // Latest assignment record for a lead
    Optional<LeadAssignment> findTopByLeadLeadIdOrderByAssignedAtDesc(Integer leadId);

    // Check whether lead has ever been assigned
    boolean existsByLeadLeadId(Integer leadId);

    // Count assignments made by a manager
    long countByAssignedById(Integer employeeId);

    // Count assignments received by an employee
    long countByAssignedToId(Integer employeeId);
}