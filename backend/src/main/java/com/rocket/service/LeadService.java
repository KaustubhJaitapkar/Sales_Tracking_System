package com.rocket.service;

import com.rocket.entity.Employee;
import com.rocket.entity.Lead;
import com.rocket.entity.LeadAssignment;
import com.rocket.entity.LeadPriority;
import com.rocket.repository.EmployeeRepository;
import com.rocket.repository.LeadAssignmentRepository;
import com.rocket.repository.LeadRepository;

import jakarta.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LeadService {

    private static final Logger logger =
            LoggerFactory.getLogger(LeadService.class);

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private LeadAssignmentRepository leadAssignmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Runs automatically when application starts.
     * Used to verify that JPA is fetching data correctly.
     */
    @PostConstruct
    public void verifyLeadFetch() {

        logger.info("========== LEAD FETCH VERIFICATION ==========");

        List<Lead> leads = leadRepository.findAll();

        logger.info("Total Leads Found : {}", leads.size());

        leads.forEach(lead ->
                logger.info(
                        "Lead ID: {}, Priority: {}, Value: {}",
                        lead.getLeadId(),
                        lead.getPriority(),
                        lead.getValue()
                )
        );

        logger.info("============================================");
    }

    /**
     * Add a new lead
     */
    public Lead addLead(Lead lead) {

        if (lead.getPriority() == null) {
            lead.setPriority(LeadPriority.Medium);
        }

        if (lead.getValue() == null) {
            lead.setValue(0);
        }

        return leadRepository.save(lead);
    }

    /**
     * Update existing lead
     */
    public Lead updateLead(Integer leadId, Lead leadDetails) {

        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Lead not found with ID: " + leadId
                        ));

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

        return leadRepository.save(lead);
    }

    /**
     * Delete lead
     */
    public boolean deleteLead(Integer leadId) {

        if (!leadRepository.existsById(leadId)) {
            throw new RuntimeException(
                    "Lead not found with ID: " + leadId
            );
        }

        leadRepository.deleteById(leadId);

        return true;
    }

    /**
     * Get lead by ID
     */
    public Lead getLeadById(Integer leadId) {

        return leadRepository.findById(leadId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Lead not found with ID: " + leadId
                        ));
    }

    /**
     * Get all leads
     */
    public List<Lead> getAllLeads() {
        return leadRepository.findAll();
    }

    /**
     * Get leads by product
     */
    public List<Lead> getLeadsByProduct(Integer productId) {
        return leadRepository.findByProductProductId(productId);
    }

    /**
     * Get leads by source
     */
    public List<Lead> getLeadsBySource(Integer sourceId) {
        return leadRepository.findBySourceSourceId(sourceId);
    }

    /**
     * Get leads by status
     */
    public List<Lead> getLeadsByStatus(Integer statusId) {
        return leadRepository.findByStatusStatusId(statusId);
    }

    /**
     * Get leads by priority
     */
    public List<Lead> getLeadsByPriority(
            LeadPriority priority
    ) {
        return leadRepository.findByPriority(priority);
    }

    /**
     * Get leads assigned to employee
     */
    public List<Lead> getLeadsAssignedToEmployee(
            Integer employeeId
    ) {
        return leadRepository.findByAssignedToId(employeeId);
    }

    /**
     * Get leads created by employee
     */
    public List<Lead> getLeadsCreatedByEmployee(
            Integer employeeId
    ) {
        return leadRepository.findByCreatedById(employeeId);
    }

    /**
     * Get leads by customer
     */
    public List<Lead> getLeadsByCustomer(
            Integer customerId
    ) {
        return leadRepository.findByCustomerCustomerId(customerId);
    }

    /**
     * Get unassigned leads
     */
    public List<Lead> getUnassignedLeads() {
        return leadRepository.findByAssignedToIsNull();
    }

    /**
     * Get assigned leads
     */
    public List<Lead> getAssignedLeads() {
        return leadRepository.findByAssignedToIsNotNull();
    }

    /**
     * Get high value leads
     */
    public List<Lead> getHighValueLeads(
            Integer minimumValue
    ) {
        return leadRepository.findByValueGreaterThan(minimumValue);
    }

    /**
     * Assign lead to employee
     */
    public LeadAssignment assignLead(
            Integer leadId,
            Integer employeeId,
            Integer assignedByEmployeeId,
            String remarks
    ) {

        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Lead not found"
                        ));

        Employee assignedTo = employeeRepository
                .findById(employeeId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Employee not found"
                        ));

        Employee assignedBy = employeeRepository
                .findById(assignedByEmployeeId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Assigning employee not found"
                        ));

        LeadAssignment assignment = new LeadAssignment();

        assignment.setLead(lead);
        assignment.setAssignedTo(assignedTo);
        assignment.setAssignedBy(assignedBy);
        assignment.setAssignedAt(LocalDateTime.now());
        assignment.setRemarks(remarks);

        lead.setAssignedTo(assignedTo);

        leadRepository.save(lead);

        logger.info(
                "Lead {} assigned to Employee {} by Employee {}",
                leadId,
                employeeId,
                assignedByEmployeeId
        );

        return leadAssignmentRepository.save(assignment);
    }

    /**
     * Reassign lead
     */
    public LeadAssignment reassignLead(
            Integer leadId,
            Integer newEmployeeId,
            Integer managerEmployeeId,
            String remarks
    ) {

        return assignLead(
                leadId,
                newEmployeeId,
                managerEmployeeId,
                remarks
        );
    }

    /**
     * Get assignment history
     */
    public List<LeadAssignment> getAssignmentHistory(
            Integer leadId
    ) {
        return leadAssignmentRepository
                .findByLeadLeadIdOrderByAssignedAtDesc(leadId);
    }

    /**
     * Manual debug method
     */
    public void printAllLeads() {

        List<Lead> leads = leadRepository.findAll();

        logger.info("========== ALL LEADS ==========");

        for (Lead lead : leads) {

            logger.info(
                    "Lead ID: {}, Priority: {}, Value: {}",
                    lead.getLeadId(),
                    lead.getPriority(),
                    lead.getValue()
            );
        }

        logger.info(
                "Total Leads Found: {}",
                leads.size()
        );
    }
}