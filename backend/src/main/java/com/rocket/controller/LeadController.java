package com.rocket.controller;

import com.rocket.entity.Lead;
import com.rocket.entity.LeadAssignment;
import com.rocket.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/leads")
public class LeadController {
    
    @Autowired
    private LeadService leadService;
    
    // Add a new lead
    @PostMapping
    public ResponseEntity<Lead> addLead(@RequestBody Lead lead) {
        Lead savedLead = leadService.addLead(lead);
        return new ResponseEntity<>(savedLead, HttpStatus.CREATED);
    }
    
    // Update an existing lead
    @PutMapping("/{id}")
    public ResponseEntity<Lead> updateLead(@PathVariable Integer id, @RequestBody Lead leadDetails) {
        Lead updatedLead = leadService.updateLead(id, leadDetails);
        if (updatedLead != null) {
            return new ResponseEntity<>(updatedLead, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // Delete a lead
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLead(@PathVariable Integer id) {
        boolean deleted = leadService.deleteLead(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // Get lead by ID
    @GetMapping("/{id}")
    public ResponseEntity<Lead> getLeadById(@PathVariable Integer id) {
        Optional<Lead> lead = leadService.getLeadById(id);
        return lead.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // Get all leads
    @GetMapping
    public ResponseEntity<List<Lead>> getAllLeads() {
        List<Lead> leads = leadService.getAllLeads();
        return new ResponseEntity<>(leads, HttpStatus.OK);
    }
    
    // Get leads by product
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Lead>> getLeadsByProduct(@PathVariable Integer productId) {
        List<Lead> leads = leadService.getLeadsByProduct(productId);
        return new ResponseEntity<>(leads, HttpStatus.OK);
    }
    
    // Get leads by source
    @GetMapping("/source/{sourceId}")
    public ResponseEntity<List<Lead>> getLeadsBySource(@PathVariable Integer sourceId) {
        List<Lead> leads = leadService.getLeadsBySource(sourceId);
        return new ResponseEntity<>(leads, HttpStatus.OK);
    }
    
    // Get leads by status
    @GetMapping("/status/{statusId}")
    public ResponseEntity<List<Lead>> getLeadsByStatus(@PathVariable Integer statusId) {
        List<Lead> leads = leadService.getLeadsByStatus(statusId);
        return new ResponseEntity<>(leads, HttpStatus.OK);
    }
    
    // Assign a lead to an employee
    @PostMapping("/{leadId}/assign/{employeeId}")
    public ResponseEntity<LeadAssignment> assignLead(@PathVariable Integer leadId, 
                                                      @PathVariable Integer employeeId,
                                                      @RequestParam Integer assignedByEmployeeId,
                                                      @RequestParam(required = false) String remarks) {
        LeadAssignment assignment = leadService.assignLead(leadId, employeeId, assignedByEmployeeId, remarks);
        if (assignment != null) {
            return new ResponseEntity<>(assignment, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // Reassign a lead to another employee (manager function)
    @PostMapping("/{leadId}/reassign/{newEmployeeId}")
    public ResponseEntity<LeadAssignment> reassignLead(@PathVariable Integer leadId, 
                                                        @PathVariable Integer newEmployeeId,
                                                        @RequestParam Integer managerEmployeeId,
                                                        @RequestParam(required = false) String remarks) {
        LeadAssignment assignment = leadService.reassignLead(leadId, newEmployeeId, managerEmployeeId, remarks);
        if (assignment != null) {
            return new ResponseEntity<>(assignment, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // Get leads assigned to an employee
    @GetMapping("/assigned-to/{employeeId}")
    public ResponseEntity<List<Lead>> getLeadsAssignedToEmployee(@PathVariable Integer employeeId) {
        List<Lead> leads = leadService.getLeadsAssignedToEmployee(employeeId);
        return new ResponseEntity<>(leads, HttpStatus.OK);
    }
    
    // Get assignment history for a lead
    @GetMapping("/{leadId}/assignment-history")
    public ResponseEntity<List<LeadAssignment>> getAssignmentHistory(@PathVariable Integer leadId) {
        List<LeadAssignment> history = leadService.getAssignmentHistory(leadId);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }
}
