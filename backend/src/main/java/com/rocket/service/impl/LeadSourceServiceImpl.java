package com.rocket.service.impl;

import com.rocket.dto.LeadSourceDTO;
import com.rocket.entity.LeadSource;
import com.rocket.mapper.EntityDTOMapper;
import com.rocket.repository.LeadSourceRepository;
import com.rocket.service.ILeadSourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LeadSourceServiceImpl implements ILeadSourceService {
    
    private final LeadSourceRepository leadSourceRepository;
    private final EntityDTOMapper mapper;
    
    @Override
    public LeadSourceDTO addLeadSource(LeadSourceDTO leadSourceDTO) {
        log.info("Adding new lead source: {}", leadSourceDTO.getSourceName());
        LeadSource leadSource = mapper.toLeadSourceEntity(leadSourceDTO);
        LeadSource saved = leadSourceRepository.save(leadSource);
        return mapper.toLeadSourceDTO(saved);
    }
    
    @Override
    public LeadSourceDTO updateLeadSource(Integer sourceId, LeadSourceDTO leadSourceDetails) {
        log.info("Updating lead source with ID: {}", sourceId);
        Optional<LeadSource> optionalSource = leadSourceRepository.findByIdActive(sourceId);
        if (optionalSource.isPresent()) {
            LeadSource source = optionalSource.get();
            if (leadSourceDetails.getSourceName() != null) {
                source.setSourceName(leadSourceDetails.getSourceName());
            }
            LeadSource updated = leadSourceRepository.save(source);
            return mapper.toLeadSourceDTO(updated);
        }
        return null;
    }
    
    @Override
    public boolean deleteLeadSource(Integer sourceId) {
        log.info("Deleting lead source with ID: {}", sourceId);
        Optional<LeadSource> optionalSource = leadSourceRepository.findByIdActive(sourceId);
        if (optionalSource.isPresent()) {
            LeadSource source = optionalSource.get();
            source.setIsActive(false);
            leadSourceRepository.save(source);
            return true;
        }
        return false;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<LeadSourceDTO> getLeadSourceById(Integer sourceId) {
        log.info("Fetching lead source with ID: {}", sourceId);
        return leadSourceRepository.findByIdActive(sourceId)
                .map(mapper::toLeadSourceDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LeadSourceDTO> getAllLeadSources() {
        log.info("Fetching all lead sources");
        return leadSourceRepository.findAllActive()
                .stream()
                .map(mapper::toLeadSourceDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<LeadSourceDTO> getLeadSourceByName(String sourceName) {
        log.info("Fetching lead source by name: {}", sourceName);
        return leadSourceRepository.findBySourceName(sourceName)
                .map(mapper::toLeadSourceDTO);
    }
}
