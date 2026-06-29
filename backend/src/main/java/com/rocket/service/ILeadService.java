package com.rocket.service;

import com.rocket.dto.LeadDTO;
import java.util.List;

public interface ILeadService {
    
    LeadDTO createLead(LeadDTO leadDTO);
    
    LeadDTO updateLead(Integer leadId, LeadDTO leadDTO);
    
    void deleteLead(Integer leadId);
    
    LeadDTO getLeadById(Integer leadId);
    
    List<LeadDTO> getAllLeads();
    
    List<LeadDTO> getLeadsByCustomerId(Integer customerId);
    
    List<LeadDTO> getLeadsByAssignedTo(Integer employeeId);
    
    List<LeadDTO> getLeadsByStatus(Integer statusId);
    
    void assignLead(Integer leadId, Integer employeeId);
    
    void updateLeadStatus(Integer leadId, Integer statusId);
    
    void changeLeadPriority(Integer leadId, String priority);
}
