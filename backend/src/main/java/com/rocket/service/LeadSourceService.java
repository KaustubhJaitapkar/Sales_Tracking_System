package com.rocket.service;

import com.rocket.entity.LeadSource;
import com.rocket.repository.LeadSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LeadSourceService {
    
    @Autowired
    private LeadSourceRepository leadSourceRepository;
    
    // Add a new lead source
    public LeadSource addLeadSource(LeadSource leadSource) {
        return leadSourceRepository.save(leadSource);
    }
    
    // Update a lead source
    public LeadSource updateLeadSource(Integer sourceId, LeadSource leadSourceDetails) {
        Optional<LeadSource> optionalSource = leadSourceRepository.findById(sourceId);
        if (optionalSource.isPresent()) {
            LeadSource source = optionalSource.get();
            if (leadSourceDetails.getSourceName() != null) {
                source.setSourceName(leadSourceDetails.getSourceName());
            }
            return leadSourceRepository.save(source);
        }
        return null;
    }
    
    // Delete a lead source
    public boolean deleteLeadSource(Integer sourceId) {
        if (leadSourceRepository.existsById(sourceId)) {
            leadSourceRepository.deleteById(sourceId);
            return true;
        }
        return false;
    }
    
    // Get lead source by ID
    public Optional<LeadSource> getLeadSourceById(Integer sourceId) {
        return leadSourceRepository.findById(sourceId);
    }
    
    // Get all lead sources
    public List<LeadSource> getAllLeadSources() {
        return leadSourceRepository.findAll();
    }
    
    // Get lead source by name
    public Optional<LeadSource> getLeadSourceByName(String sourceName) {
        return leadSourceRepository.findBySourceName(sourceName);
    }
}
