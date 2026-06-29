package com.rocket.service;

import com.rocket.dto.LeadSourceDTO;
import java.util.List;
import java.util.Optional;

public interface ILeadSourceService {
    
    LeadSourceDTO addLeadSource(LeadSourceDTO leadSourceDTO);
    
    LeadSourceDTO updateLeadSource(Integer sourceId, LeadSourceDTO leadSourceDetails);
    
    boolean deleteLeadSource(Integer sourceId);
    
    Optional<LeadSourceDTO> getLeadSourceById(Integer sourceId);
    
    List<LeadSourceDTO> getAllLeadSources();
    
    Optional<LeadSourceDTO> getLeadSourceByName(String sourceName);
}
