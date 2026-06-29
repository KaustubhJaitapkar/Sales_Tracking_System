package com.rocket.service.impl;

import com.rocket.dto.LeadDTO;
import com.rocket.entity.*;
import com.rocket.exception.ResourceNotFoundException;
import com.rocket.exception.ValidationException;
import com.rocket.mapper.EntityDTOMapper;
import com.rocket.repository.*;
import com.rocket.service.ILeadService;
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
public class LeadServiceImpl implements ILeadService {
    
    private final LeadRepository leadRepository;
    private final CustomerRepository customerRepository;
    private final LeadSourceRepository leadSourceRepository;
    private final ProductDetailsRepository productDetailsRepository;
    private final EmployeeRepository employeeRepository;
    private final LeadStatusRepository leadStatusRepository;
    private final EntityDTOMapper mapper;
    
    @Override
    public LeadDTO createLead(LeadDTO leadDTO) {
        log.info("Creating lead for customer ID: {}", leadDTO.getCustomerId());
        
        validateLeadDTO(leadDTO);
        
        // Fetch and validate related entities
        Customer customer = customerRepository.findById(leadDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Customer with ID " + leadDTO.getCustomerId() + " not found"));
        
        LeadSource source = leadSourceRepository.findById(leadDTO.getSourceId())
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Lead source with ID " + leadDTO.getSourceId() + " not found"));
        
        ProductDetails product = productDetailsRepository.findById(leadDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Product with ID " + leadDTO.getProductId() + " not found"));
        
        Employee createdBy = employeeRepository.findById(leadDTO.getCreatedBy())
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Employee with ID " + leadDTO.getCreatedBy() + " not found"));
        
        LeadStatus status = leadStatusRepository.findById(leadDTO.getStatusId())
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Lead status with ID " + leadDTO.getStatusId() + " not found"));
        
        // Create lead entity
        Lead lead = Lead.builder()
                .customer(customer)
                .source(source)
                .product(product)
                .createdBy(createdBy)
                .status(status)
                .description(leadDTO.getDescription())
                .priority(leadDTO.getPriority() != null ? leadDTO.getPriority() : LeadPriority.Medium)
                .value(leadDTO.getValue() != null ? leadDTO.getValue() : 0)
                .build();
        
        // Set assigned to if provided
        if (leadDTO.getAssignedTo() != null) {
            Employee assignedTo = employeeRepository.findById(leadDTO.getAssignedTo())
                    .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee with ID " + leadDTO.getAssignedTo() + " not found"));
            lead.setAssignedTo(assignedTo);
        }
        
        Lead savedLead = leadRepository.save(lead);
        log.info("Lead created successfully with ID: {}", savedLead.getLeadId());
        
        return mapper.toLeadDTO(savedLead);
    }
    
    @Override
    public LeadDTO updateLead(Integer leadId, LeadDTO leadDTO) {
        log.info("Updating lead with ID: {}", leadId);
        
        Lead lead = leadRepository.findByIdActive(leadId)
                .orElseThrow(() -> new ResourceNotFoundException("Lead with ID " + leadId + " not found"));
        
        // Update fields if provided
        if (leadDTO.getDescription() != null) {
            lead.setDescription(leadDTO.getDescription());
        }
        if (leadDTO.getPriority() != null) {
            lead.setPriority(leadDTO.getPriority());
        }
        if (leadDTO.getValue() != null) {
            lead.setValue(leadDTO.getValue());
        }
        
        // Update status if provided
        if (leadDTO.getStatusId() != null) {
            LeadStatus status = leadStatusRepository.findById(leadDTO.getStatusId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                        "Lead status with ID " + leadDTO.getStatusId() + " not found"));
            lead.setStatus(status);
        }
        
        // Update assigned employee if provided
        if (leadDTO.getAssignedTo() != null) {
            Employee assignedTo = employeeRepository.findById(leadDTO.getAssignedTo())
                    .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee with ID " + leadDTO.getAssignedTo() + " not found"));
            lead.setAssignedTo(assignedTo);
        }
        
        Lead updatedLead = leadRepository.save(lead);
        log.info("Lead updated successfully");
        
        return mapper.toLeadDTO(updatedLead);
    }
    
    @Override
    public void deleteLead(Integer leadId) {
        log.info("Deleting lead with ID: {}", leadId);
        
        Lead lead = leadRepository.findByIdActive(leadId)
                .orElseThrow(() -> new ResourceNotFoundException("Lead with ID " + leadId + " not found"));
        lead.setIsActive(false);
        leadRepository.save(lead);
        
        log.info("Lead deleted successfully");
    }
    
    @Override
    @Transactional(readOnly = true)
    public LeadDTO getLeadById(Integer leadId) {
        log.info("Fetching lead with ID: {}", leadId);
        
        Lead lead = leadRepository.findByIdActive(leadId)
                .orElseThrow(() -> new ResourceNotFoundException("Lead with ID " + leadId + " not found"));
        
        return mapper.toLeadDTO(lead);
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
    public List<LeadDTO> getLeadsByCustomerId(Integer customerId) {
        log.info("Fetching leads for customer ID: {}", customerId);
        
        if (!customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException("Customer with ID " + customerId + " not found");
        }
        
        return leadRepository.findByCustomerCustomerId(customerId)
                .stream()
                .map(mapper::toLeadDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LeadDTO> getLeadsByAssignedTo(Integer employeeId) {
        log.info("Fetching leads assigned to employee ID: {}", employeeId);
        
        if (!employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("Employee with ID " + employeeId + " not found");
        }
        
        return leadRepository.findByAssignedToId(employeeId)
                .stream()
                .map(mapper::toLeadDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LeadDTO> getLeadsByStatus(Integer statusId) {
        log.info("Fetching leads with status ID: {}", statusId);
        
        if (!leadStatusRepository.existsById(statusId)) {
            throw new ResourceNotFoundException("Lead status with ID " + statusId + " not found");
        }
        
        return leadRepository.findByStatusStatusId(statusId)
                .stream()
                .map(mapper::toLeadDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public void assignLead(Integer leadId, Integer employeeId) {
        log.info("Assigning lead ID {} to employee ID {}", leadId, employeeId);
        
        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new ResourceNotFoundException("Lead with ID " + leadId + " not found"));
        
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with ID " + employeeId + " not found"));
        
        lead.setAssignedTo(employee);
        leadRepository.save(lead);
        
        log.info("Lead assigned successfully");
    }
    
    @Override
    public void updateLeadStatus(Integer leadId, Integer statusId) {
        log.info("Updating lead {} status to {}", leadId, statusId);
        
        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new ResourceNotFoundException("Lead with ID " + leadId + " not found"));
        
        LeadStatus status = leadStatusRepository.findById(statusId)
                .orElseThrow(() -> new ResourceNotFoundException("Lead status with ID " + statusId + " not found"));
        
        lead.setStatus(status);
        leadRepository.save(lead);
        
        log.info("Lead status updated successfully");
    }
    
    @Override
    public void changeLeadPriority(Integer leadId, String priority) {
        log.info("Changing lead {} priority to {}", leadId, priority);
        
        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new ResourceNotFoundException("Lead with ID " + leadId + " not found"));
        
        try {
            LeadPriority leadPriority = LeadPriority.valueOf(priority);
            lead.setPriority(leadPriority);
            leadRepository.save(lead);
            log.info("Lead priority changed successfully");
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Invalid priority: " + priority + ". Must be one of: " + 
                    java.util.Arrays.toString(LeadPriority.values()));
        }
    }
    
    private void validateLeadDTO(LeadDTO dto) {
        if (dto == null) {
            throw new ValidationException("Lead data cannot be null");
        }
        if (dto.getCustomerId() == null) {
            throw new ValidationException("Customer ID is required");
        }
        if (dto.getSourceId() == null) {
            throw new ValidationException("Source ID is required");
        }
        if (dto.getProductId() == null) {
            throw new ValidationException("Product ID is required");
        }
        if (dto.getCreatedBy() == null) {
            throw new ValidationException("Created by (Employee ID) is required");
        }
        if (dto.getStatusId() == null) {
            throw new ValidationException("Status ID is required");
        }
    }
}
