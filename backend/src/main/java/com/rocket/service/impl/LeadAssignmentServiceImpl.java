package com.rocket.service.impl;

import com.rocket.dto.LeadAssignmentDTO;
import com.rocket.entity.Employee;
import com.rocket.entity.Lead;
import com.rocket.entity.LeadAssignment;
import com.rocket.mapper.EntityDTOMapper;
import com.rocket.repository.EmployeeRepository;
import com.rocket.repository.LeadAssignmentRepository;
import com.rocket.repository.LeadRepository;
import com.rocket.service.ILeadAssignmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LeadAssignmentServiceImpl implements ILeadAssignmentService {
    
    private final LeadAssignmentRepository leadAssignmentRepository;
    private final LeadRepository leadRepository;
    private final EmployeeRepository employeeRepository;
    private final EntityDTOMapper mapper;
    
    @Override
    public LeadAssignmentDTO assignLead(Integer leadId, Integer assignedToId, Integer assignedById, String remarks) {
        log.info("Assigning lead {} to employee {}", leadId, assignedToId);
        
        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new RuntimeException("Lead not found: " + leadId));
        
        Employee assignedTo = employeeRepository.findById(assignedToId)
                .orElseThrow(() -> new RuntimeException("Employee not found: " + assignedToId));
        
        Employee assignedBy = employeeRepository.findById(assignedById)
                .orElseThrow(() -> new RuntimeException("Employee not found: " + assignedById));
        
        LeadAssignment latestAssignment = leadAssignmentRepository
                .findTopByLeadLeadIdOrderByAssignedAtDesc(leadId)
                .orElse(null);
        
        if (latestAssignment != null && latestAssignment.getAssignedTo().getId().equals(assignedToId)) {
            throw new RuntimeException("Lead is already assigned to this employee");
        }
        
        LeadAssignment assignment = new LeadAssignment();
        assignment.setLead(lead);
        assignment.setAssignedTo(assignedTo);
        assignment.setAssignedBy(assignedBy);
        assignment.setRemarks(remarks);
        
        LeadAssignment saved = leadAssignmentRepository.save(assignment);
        return mapper.toLeadAssignmentDTO(saved);
    }
    
    @Override
    public LeadAssignmentDTO reassignLead(Integer leadId, Integer assignedToId, Integer assignedById, String remarks) {
        log.info("Reassigning lead {} to employee {}", leadId, assignedToId);
        return assignLead(leadId, assignedToId, assignedById, remarks);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LeadAssignmentDTO> getLeadAssignmentHistory(Integer leadId) {
        log.info("Fetching assignment history for lead {}", leadId);
        return leadAssignmentRepository.findByLeadLeadIdOrderByAssignedAtDesc(leadId)
                .stream()
                .map(mapper::toLeadAssignmentDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LeadAssignmentDTO> getAssignmentsByEmployee(Integer employeeId) {
        log.info("Fetching assignments for employee {}", employeeId);
        return leadAssignmentRepository.findByAssignedToIdOrderByAssignedAtDesc(employeeId)
                .stream()
                .map(mapper::toLeadAssignmentDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LeadAssignmentDTO> getAssignmentsByManager(Integer managerId) {
        log.info("Fetching assignments made by manager {}", managerId);
        return leadAssignmentRepository.findByAssignedByIdOrderByAssignedAtDesc(managerId)
                .stream()
                .map(mapper::toLeadAssignmentDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public LeadAssignmentDTO getCurrentAssignment(Integer leadId) {
        log.info("Fetching current assignment for lead {}", leadId);
        return leadAssignmentRepository.findTopByLeadLeadIdOrderByAssignedAtDesc(leadId)
                .map(mapper::toLeadAssignmentDTO)
                .orElseThrow(() -> new RuntimeException("No assignment found for lead: " + leadId));
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean isLeadAssigned(Integer leadId) {
        log.info("Checking if lead {} has assignments", leadId);
        return leadAssignmentRepository.existsByLeadLeadId(leadId);
    }
}
