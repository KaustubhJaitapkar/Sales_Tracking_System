package com.rocket.repository;

import com.rocket.entity.LeadAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeadAssignmentRepository extends JpaRepository<LeadAssignment, Integer> {

    // Complete assignment history of a lead
    @Query("SELECT la FROM LeadAssignment la WHERE la.lead.leadId = ?1 AND la.isActive = true ORDER BY la.assignedAt DESC")
    List<LeadAssignment> findByLeadLeadIdOrderByAssignedAtDesc(Integer leadId);

    // All leads currently/historically assigned to an employee
    @Query("SELECT la FROM LeadAssignment la WHERE la.assignedTo.id = ?1 AND la.isActive = true ORDER BY la.assignedAt DESC")
    List<LeadAssignment> findByAssignedToIdOrderByAssignedAtDesc(Integer employeeId);

    // All assignments performed by a manager
    @Query("SELECT la FROM LeadAssignment la WHERE la.assignedBy.id = ?1 AND la.isActive = true ORDER BY la.assignedAt DESC")
    List<LeadAssignment> findByAssignedByIdOrderByAssignedAtDesc(Integer employeeId);

    // Latest assignment record for a lead
    @Query("SELECT la FROM LeadAssignment la WHERE la.lead.leadId = ?1 AND la.isActive = true ORDER BY la.assignedAt DESC LIMIT 1")
    Optional<LeadAssignment> findTopByLeadLeadIdOrderByAssignedAtDesc(Integer leadId);

    // Check whether lead has ever been assigned
    @Query("SELECT CASE WHEN COUNT(la) > 0 THEN true ELSE false END FROM LeadAssignment la WHERE la.lead.leadId = ?1 AND la.isActive = true")
    boolean existsByLeadLeadId(Integer leadId);

    // Count assignments made by a manager
    @Query("SELECT COUNT(la) FROM LeadAssignment la WHERE la.assignedBy.id = ?1 AND la.isActive = true")
    long countByAssignedById(Integer employeeId);

    // Count assignments received by an employee
    @Query("SELECT COUNT(la) FROM LeadAssignment la WHERE la.assignedTo.id = ?1 AND la.isActive = true")
    long countByAssignedToId(Integer employeeId);

    @Query("SELECT la FROM LeadAssignment la WHERE la.lead.leadId = ?1 AND la.isActive = true")
    List<LeadAssignment> findByLeadLeadId(Integer leadId);
    
    @Query("SELECT la FROM LeadAssignment la WHERE la.isActive = true")
    List<LeadAssignment> findAllActive();
    
    @Query("SELECT la FROM LeadAssignment la WHERE la.assignmentId = ?1 AND la.isActive = true")
    Optional<LeadAssignment> findByIdActive(Integer assignmentId);
}