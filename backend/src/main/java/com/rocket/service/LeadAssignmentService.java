package com.rocket.service;

import com.rocket.entity.Employee;
import com.rocket.entity.Lead;
import com.rocket.entity.LeadAssignment;
import com.rocket.repository.EmployeeRepository;
import com.rocket.repository.LeadAssignmentRepository;
import com.rocket.repository.LeadRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LeadAssignmentService {

    private final LeadAssignmentRepository leadAssignmentRepository;
    private final LeadRepository leadRepository;
    private final EmployeeRepository employeeRepository;

    public LeadAssignmentService(
            LeadAssignmentRepository leadAssignmentRepository,
            LeadRepository leadRepository,
            EmployeeRepository employeeRepository) {

        this.leadAssignmentRepository = leadAssignmentRepository;
        this.leadRepository = leadRepository;
        this.employeeRepository = employeeRepository;
    }

    /**
     * Assign lead to employee
     */
    public LeadAssignment assignLead(
            Integer leadId,
            Integer assignedToId,
            Integer assignedById,
            String remarks) {

        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() ->
                        new RuntimeException("Lead not found: " + leadId));

        Employee assignedTo = employeeRepository.findById(assignedToId)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found: " + assignedToId));

        Employee assignedBy = employeeRepository.findById(assignedById)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found: " + assignedById));

        LeadAssignment latestAssignment =
                leadAssignmentRepository
                        .findTopByLeadLeadIdOrderByAssignedAtDesc(leadId)
                        .orElse(null);

        if (latestAssignment != null &&
            latestAssignment.getAssignedTo().getId().equals(assignedToId)) {

            throw new RuntimeException(
                    "Lead is already assigned to this employee");
        }

        LeadAssignment assignment = new LeadAssignment();
        assignment.setLead(lead);
        assignment.setAssignedTo(assignedTo);
        assignment.setAssignedBy(assignedBy);
        assignment.setRemarks(remarks);

        return leadAssignmentRepository.save(assignment);
    }

    /**
     * Reassign lead
     */
    public LeadAssignment reassignLead(
            Integer leadId,
            Integer assignedToId,
            Integer assignedById,
            String remarks) {

        return assignLead(
                leadId,
                assignedToId,
                assignedById,
                remarks
        );
    }

    /**
     * Get assignment history of a lead
     */
    @Transactional(readOnly = true)
    public List<LeadAssignment> getLeadAssignmentHistory(Integer leadId) {

        return leadAssignmentRepository
                .findByLeadLeadIdOrderByAssignedAtDesc(leadId);
    }

    /**
     * Get all assignments for an employee
     */
    @Transactional(readOnly = true)
    public List<LeadAssignment> getAssignmentsByEmployee(Integer employeeId) {

        return leadAssignmentRepository
                .findByAssignedToIdOrderByAssignedAtDesc(employeeId);
    }

    /**
     * Get all assignments made by manager
     */
    @Transactional(readOnly = true)
    public List<LeadAssignment> getAssignmentsByManager(Integer managerId) {

        return leadAssignmentRepository
                .findByAssignedByIdOrderByAssignedAtDesc(managerId);
    }

    /**
     * Get current assignment of a lead
     */
    @Transactional(readOnly = true)
    public LeadAssignment getCurrentAssignment(Integer leadId) {

        return leadAssignmentRepository
                .findTopByLeadLeadIdOrderByAssignedAtDesc(leadId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "No assignment found for lead: " + leadId));
    }

    /**
     * Check whether lead has assignment history
     */
    @Transactional(readOnly = true)
    public boolean isLeadAssigned(Integer leadId) {

        return leadAssignmentRepository
                .existsByLeadLeadId(leadId);
    }
}