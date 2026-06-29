package com.rocket.service;

import com.rocket.dto.LeadAssignmentDTO;
import java.util.List;

public interface ILeadAssignmentService {
    
    LeadAssignmentDTO assignLead(Integer leadId, Integer assignedToId, Integer assignedById, String remarks);
    
    LeadAssignmentDTO reassignLead(Integer leadId, Integer assignedToId, Integer assignedById, String remarks);
    
    List<LeadAssignmentDTO> getLeadAssignmentHistory(Integer leadId);
    
    List<LeadAssignmentDTO> getAssignmentsByEmployee(Integer employeeId);
    
    List<LeadAssignmentDTO> getAssignmentsByManager(Integer managerId);
    
    LeadAssignmentDTO getCurrentAssignment(Integer leadId);
    
    boolean isLeadAssigned(Integer leadId);
}
