package com.rocket.service;

import com.rocket.entity.Lead;
import com.rocket.entity.LeadAssignment;
import com.rocket.entity.Employee;
import com.rocket.repository.LeadRepository;
import com.rocket.repository.LeadAssignmentRepository;
import com.rocket.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LeadService {
    
    @Autowired
    private LeadRepository leadRepository;
    
    @Autowired
    private LeadAssignmentRepository leadAssignmentRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;

    // Add a new lead
    public Lead addLead(Lead lead) {
        lead.setCreatedAt(LocalDateTime.now());
        lead.setUpdatedAt(LocalDateTime.now());
        if (lead.getPriority() == null) {
            lead.setPriority("Medium");
        }
        if (lead.getValue() == null) {
            lead.setValue(0);
        }
        return leadRepository.save(lead);
    }
    
    // Update an existing lead
    public Lead updateLead(Integer leadId, Lead leadDetails) {
        Optional<Lead> optionalLead = leadRepository.findById(leadId);
        if (optionalLead.isPresent()) {
            Lead lead = optionalLead.get();
            if (leadDetails.getCustomer() != null) {
                lead.setCustomer(leadDetails.getCustomer());
            }
            if (leadDetails.getSource() != null) {
                lead.setSource(leadDetails.getSource());
            }
            if (leadDetails.getProduct() != null) {
                lead.setProduct(leadDetails.getProduct());
            }
            if (leadDetails.getStatus() != null) {
                lead.setStatus(leadDetails.getStatus());
            }
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
            return leadRepository.save(lead);
        }
        return null;
    }
    
    // Delete a lead
    public boolean deleteLead(Integer leadId) {
        if (leadRepository.existsById(leadId)) {
            leadRepository.deleteById(leadId);
            return true;
        }
        return false;
    }
    
    // Get lead by ID
    public Optional<Lead> getLeadById(Integer leadId) {
        return leadRepository.findById(leadId);
    }
    
    // Get all leads
    public List<Lead> getAllLeads() {
        return leadRepository.findAll();
    }
    
    // Get leads by product
    public List<Lead> getLeadsByProduct(Integer productId) {
        return leadRepository.findByProductProductId(productId);
    }
    
    // Get leads by source
    public List<Lead> getLeadsBySource(Integer sourceId) {
        return leadRepository.findBySourceSourceId(sourceId);
    }
    
    // Get leads by status
    public List<Lead> getLeadsByStatus(Integer statusId) {
        return leadRepository.findByStatusStatusId(statusId);
    }
    
    // Assign a lead to an employee
    public LeadAssignment assignLead(Integer leadId, Integer employeeId, Integer assignedByEmployeeId, String remarks) {
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
            
            return leadAssignmentRepository.save(assignment);
        }
        return null;
    }
    
    // Reassign a lead to another employee (manager function)
    public LeadAssignment reassignLead(Integer leadId, Integer newEmployeeId, Integer managerEmployeeId, String remarks) {
        return assignLead(leadId, newEmployeeId, managerEmployeeId, remarks);
    }
    
    // Get leads assigned to an employee
    public List<Lead> getLeadsAssignedToEmployee(Integer employeeId) {
        return leadRepository.findByAssignedToId(employeeId);
    }
    
    // Get leads created by an employee
    public List<Lead> getLeadsCreatedByEmployee(Integer employeeId) {
        return leadRepository.findByCreatedById(employeeId);
    }
    
    // Get assignment history for a lead
    public List<LeadAssignment> getAssignmentHistory(Integer leadId) {
        return leadAssignmentRepository.findByLeadLeadId(leadId);
    }

    public List<Lead> getLeadsByEmployee(int employeeId) {
        return leadRepository.findByAssignedToId(employeeId);
    }
}
