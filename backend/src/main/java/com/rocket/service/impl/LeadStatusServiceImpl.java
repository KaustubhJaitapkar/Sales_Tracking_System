package com.rocket.service.impl;

import com.rocket.dto.LeadStatusDTO;
import com.rocket.entity.LeadStatus;
import com.rocket.mapper.EntityDTOMapper;
import com.rocket.repository.LeadStatusRepository;
import com.rocket.service.ILeadStatusService;
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
public class LeadStatusServiceImpl implements ILeadStatusService {
    
    private final LeadStatusRepository leadStatusRepository;
    private final EntityDTOMapper mapper;
    
    @Override
    public LeadStatusDTO addLeadStatus(LeadStatusDTO leadStatusDTO) {
        log.info("Adding new lead status: {}", leadStatusDTO.getStatusName());
        LeadStatus leadStatus = mapper.toLeadStatusEntity(leadStatusDTO);
        LeadStatus saved = leadStatusRepository.save(leadStatus);
        return mapper.toLeadStatusDTO(saved);
    }
    
    @Override
    public LeadStatusDTO updateLeadStatus(Integer statusId, LeadStatusDTO leadStatusDetails) {
        log.info("Updating lead status with ID: {}", statusId);
        Optional<LeadStatus> optionalStatus = leadStatusRepository.findByIdActive(statusId);
        if (optionalStatus.isPresent()) {
            LeadStatus status = optionalStatus.get();
            if (leadStatusDetails.getStatusName() != null) {
                status.setStatusName(leadStatusDetails.getStatusName());
            }
            LeadStatus updated = leadStatusRepository.save(status);
            return mapper.toLeadStatusDTO(updated);
        }
        return null;
    }
    
    @Override
    public boolean deleteLeadStatus(Integer statusId) {
        log.info("Deleting lead status with ID: {}", statusId);
        Optional<LeadStatus> optionalStatus = leadStatusRepository.findByIdActive(statusId);
        if (optionalStatus.isPresent()) {
            LeadStatus status = optionalStatus.get();
            status.setIsActive(false);
            leadStatusRepository.save(status);
            return true;
        }
        return false;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<LeadStatusDTO> getLeadStatusById(Integer statusId) {
        log.info("Fetching lead status with ID: {}", statusId);
        return leadStatusRepository.findByIdActive(statusId)
                .map(mapper::toLeadStatusDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LeadStatusDTO> getAllLeadStatuses() {
        log.info("Fetching all lead statuses");
        return leadStatusRepository.findAllActive()
                .stream()
                .map(mapper::toLeadStatusDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<LeadStatusDTO> getLeadStatusByName(String statusName) {
        log.info("Fetching lead status by name: {}", statusName);
        return leadStatusRepository.findByStatusName(statusName)
                .map(mapper::toLeadStatusDTO);
    }
}
