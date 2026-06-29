package com.rocket.service;

import com.rocket.dto.LeadStatusDTO;
import java.util.List;
import java.util.Optional;

public interface ILeadStatusService {
    
    LeadStatusDTO addLeadStatus(LeadStatusDTO leadStatusDTO);
    
    LeadStatusDTO updateLeadStatus(Integer statusId, LeadStatusDTO leadStatusDetails);
    
    boolean deleteLeadStatus(Integer statusId);
    
    Optional<LeadStatusDTO> getLeadStatusById(Integer statusId);
    
    List<LeadStatusDTO> getAllLeadStatuses();
    
    Optional<LeadStatusDTO> getLeadStatusByName(String statusName);
}
