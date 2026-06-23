package com.rocket.repository;

import com.rocket.entity.LeadAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LeadAssignmentRepository extends JpaRepository<LeadAssignment, Integer> {
    
    List<LeadAssignment> findByLeadLeadId(Integer leadId);
    
    List<LeadAssignment> findByAssignedToId(Integer employeeId);
    
    List<LeadAssignment> findByAssignedById(Integer employeeId);
}
