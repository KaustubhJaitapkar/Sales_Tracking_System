package com.rocket.service;

import com.rocket.dto.LeadDTO;
import com.rocket.dto.LeadAssignmentDTO;
import java.util.List;
import java.util.Optional;

public interface ILeadEntityService {
    
    LeadDTO addLead(LeadDTO leadDTO);
    
    LeadDTO updateLead(Integer leadId, LeadDTO leadDetails);
    
    boolean deleteLead(Integer leadId);
    
    Optional<LeadDTO> getLeadById(Integer leadId);
    
    List<LeadDTO> getAllLeads();
    
    List<LeadDTO> getLeadsByProduct(Integer productId);
    
    List<LeadDTO> getLeadsBySource(Integer sourceId);
    
    List<LeadDTO> getLeadsByStatus(Integer statusId);
    
    LeadAssignmentDTO assignLead(Integer leadId, Integer employeeId, Integer assignedByEmployeeId, String remarks);
    
    LeadAssignmentDTO reassignLead(Integer leadId, Integer newEmployeeId, Integer managerEmployeeId, String remarks);
    
    List<LeadDTO> getLeadsAssignedToEmployee(Integer employeeId);
    
    List<LeadDTO> getLeadsCreatedByEmployee(Integer employeeId);
    
    List<LeadAssignmentDTO> getAssignmentHistory(Integer leadId);
}
