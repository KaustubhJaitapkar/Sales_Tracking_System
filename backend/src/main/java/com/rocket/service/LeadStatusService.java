package com.rocket.service;

import com.rocket.entity.LeadStatus;
import com.rocket.repository.LeadStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LeadStatusService {
    
    @Autowired
    private LeadStatusRepository leadStatusRepository;
    
    // Add a new lead status
    public LeadStatus addLeadStatus(LeadStatus leadStatus) {
        return leadStatusRepository.save(leadStatus);
    }
    
    // Update a lead status
    public LeadStatus updateLeadStatus(Integer statusId, LeadStatus leadStatusDetails) {
        Optional<LeadStatus> optionalStatus = leadStatusRepository.findById(statusId);
        if (optionalStatus.isPresent()) {
            LeadStatus status = optionalStatus.get();
            if (leadStatusDetails.getStatusName() != null) {
                status.setStatusName(leadStatusDetails.getStatusName());
            }
            return leadStatusRepository.save(status);
        }
        return null;
    }
    
    // Delete a lead status
    public boolean deleteLeadStatus(Integer statusId) {
        if (leadStatusRepository.existsById(statusId)) {
            leadStatusRepository.deleteById(statusId);
            return true;
        }
        return false;
    }
    
    // Get lead status by ID
    public Optional<LeadStatus> getLeadStatusById(Integer statusId) {
        return leadStatusRepository.findById(statusId);
    }
    
    // Get all lead statuses
    public List<LeadStatus> getAllLeadStatuses() {
        return leadStatusRepository.findAll();
    }
    
    // Get lead status by name
    public Optional<LeadStatus> getLeadStatusByName(String statusName) {
        return leadStatusRepository.findByStatusName(statusName);
    }
}
