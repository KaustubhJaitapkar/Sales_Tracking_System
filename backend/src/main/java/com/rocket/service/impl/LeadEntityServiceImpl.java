package com.rocket.service.impl;

import com.rocket.dto.LeadDTO;
import com.rocket.dto.LeadAssignmentDTO;
import com.rocket.entity.Lead;
import com.rocket.entity.LeadAssignment;
import com.rocket.entity.Employee;
import com.rocket.entity.LeadPriority;
import com.rocket.mapper.EntityDTOMapper;
import com.rocket.repository.LeadRepository;
import com.rocket.repository.LeadAssignmentRepository;
import com.rocket.repository.EmployeeRepository;
import com.rocket.service.ILeadEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LeadEntityServiceImpl implements ILeadEntityService {
    
    private final LeadRepository leadRepository;
    private final LeadAssignmentRepository leadAssignmentRepository;
    private final EmployeeRepository employeeRepository;
    private final EntityDTOMapper mapper;
    
    @Override
    public LeadDTO addLead(LeadDTO leadDTO) {
        log.info("Adding new lead");
        Lead lead = mapper.toLeadEntity(leadDTO);
        Lead saved = leadRepository.save(lead);
        return mapper.toLeadDTO(saved);
    }
    
    @Override
    public LeadDTO updateLead(Integer leadId, LeadDTO leadDetails) {
        log.info("Updating lead with ID: {}", leadId);
        Optional<Lead> optionalLead = leadRepository.findByIdActive(leadId);
        if (optionalLead.isPresent()) {
            Lead lead = optionalLead.get();
            if (leadDetails.getPriority() != null) {
                lead.setPriority(leadDetails.getPriority());
            }
            if (leadDetails.getValue() != null) {
                lead.setValue(leadDetails.getValue());
            }
            if (leadDetails.getDescription() != null) {
                lead.setDescription(leadDetails.getDescription());
            }
            lead.setUpdatedAt(LocalDateTime.now());
            Lead updated = leadRepository.save(lead);
            return mapper.toLeadDTO(updated);
        }
        return null;
    }
    
    @Override
    public boolean deleteLead(Integer leadId) {
        log.info("Deleting lead with ID: {}", leadId);
        Optional<Lead> optionalLead = leadRepository.findByIdActive(leadId);
        if (optionalLead.isPresent()) {
            Lead lead = optionalLead.get();
            lead.setIsActive(false);
            leadRepository.save(lead);
            return true;
        }
        return false;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<LeadDTO> getLeadById(Integer leadId) {
        log.info("Fetching lead with ID: {}", leadId);
        return leadRepository.findByIdActive(leadId)
                .map(mapper::toLeadDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LeadDTO> getAllLeads() {
        log.info("Fetching all leads");
        return leadRepository.findAllActive()
                .stream()
                .map(mapper::toLeadDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LeadDTO> getLeadsByProduct(Integer productId) {
        log.info("Fetching leads by product ID: {}", productId);
        return leadRepository.findByProductProductId(productId)
                .stream()
                .map(mapper::toLeadDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LeadDTO> getLeadsBySource(Integer sourceId) {
        log.info("Fetching leads by source ID: {}", sourceId);
        return leadRepository.findBySourceSourceId(sourceId)
                .stream()
                .map(mapper::toLeadDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LeadDTO> getLeadsByStatus(Integer statusId) {
        log.info("Fetching leads by status ID: {}", statusId);
        return leadRepository.findByStatusStatusId(statusId)
                .stream()
                .map(mapper::toLeadDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public LeadAssignmentDTO assignLead(Integer leadId, Integer employeeId, Integer assignedByEmployeeId, String remarks) {
        log.info("Assigning lead {} to employee {}", leadId, employeeId);
        Optional<Lead> lead = leadRepository.findById(leadId);
        Optional<Employee> assignedTo = employeeRepository.findById(employeeId);
        Optional<Employee> assignedBy = employeeRepository.findById(assignedByEmployeeId);
        
        if (lead.isPresent() && assignedTo.isPresent() && assignedBy.isPresent()) {
            LeadAssignment assignment = new LeadAssignment();
            assignment.setLead(lead.get());
            assignment.setAssignedTo(assignedTo.get());
            assignment.setAssignedBy(assignedBy.get());
            assignment.setAssignedAt(LocalDateTime.now());
            assignment.setRemarks(remarks);
            
            // Update lead's assignedTo field
            lead.get().setAssignedTo(assignedTo.get());
            leadRepository.save(lead.get());
            
            LeadAssignment saved = leadAssignmentRepository.save(assignment);
            return mapper.toLeadAssignmentDTO(saved);
        }
        return null;
    }
    
    @Override
    public LeadAssignmentDTO reassignLead(Integer leadId, Integer newEmployeeId, Integer managerEmployeeId, String remarks) {
        log.info("Reassigning lead {} to employee {}", leadId, newEmployeeId);
        return assignLead(leadId, newEmployeeId, managerEmployeeId, remarks);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LeadDTO> getLeadsAssignedToEmployee(Integer employeeId) {
        log.info("Fetching leads assigned to employee {}", employeeId);
        return leadRepository.findByAssignedToId(employeeId)
                .stream()
                .map(mapper::toLeadDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LeadDTO> getLeadsCreatedByEmployee(Integer employeeId) {
        log.info("Fetching leads created by employee {}", employeeId);
        return leadRepository.findByCreatedById(employeeId)
                .stream()
                .map(mapper::toLeadDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LeadAssignmentDTO> getAssignmentHistory(Integer leadId) {
        log.info("Fetching assignment history for lead {}", leadId);
        return leadAssignmentRepository.findByLeadLeadId(leadId)
                .stream()
                .map(mapper::toLeadAssignmentDTO)
                .collect(Collectors.toList());
    }
}
