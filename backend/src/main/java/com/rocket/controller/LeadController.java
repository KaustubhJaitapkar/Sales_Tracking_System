package com.rocket.controller;

import com.rocket.dto.LeadDTO;
import com.rocket.dto.LeadAssignmentDTO;
import com.rocket.service.ILeadEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/leads")
@RequiredArgsConstructor
public class LeadController {
    
    private final ILeadEntityService leadService;
    
    // Add a new lead
    @PostMapping
    public ResponseEntity<LeadDTO> addLead(@RequestBody LeadDTO leadDTO) {
        LeadDTO savedLead = leadService.addLead(leadDTO);
        return new ResponseEntity<>(savedLead, HttpStatus.CREATED);
    }
    
    // Update an existing lead
    @PutMapping("/{id}")
    public ResponseEntity<LeadDTO> updateLead(@PathVariable Integer id, @RequestBody LeadDTO leadDetails) {
        LeadDTO updatedLead = leadService.updateLead(id, leadDetails);
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
    public ResponseEntity<LeadDTO> getLeadById(@PathVariable Integer id) {
        Optional<LeadDTO> lead = leadService.getLeadById(id);
        return lead.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // Get all leads
    @GetMapping
    public ResponseEntity<List<LeadDTO>> getAllLeads() {
        List<LeadDTO> leads = leadService.getAllLeads();
        return new ResponseEntity<>(leads, HttpStatus.OK);
    }
    
    // Get leads by product
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<LeadDTO>> getLeadsByProduct(@PathVariable Integer productId) {
        List<LeadDTO> leads = leadService.getLeadsByProduct(productId);
        return new ResponseEntity<>(leads, HttpStatus.OK);
    }
    
    // Get leads by source
    @GetMapping("/source/{sourceId}")
    public ResponseEntity<List<LeadDTO>> getLeadsBySource(@PathVariable Integer sourceId) {
        List<LeadDTO> leads = leadService.getLeadsBySource(sourceId);
        return new ResponseEntity<>(leads, HttpStatus.OK);
    }
    
    // Get leads by status
    @GetMapping("/status/{statusId}")
    public ResponseEntity<List<LeadDTO>> getLeadsByStatus(@PathVariable Integer statusId) {
        List<LeadDTO> leads = leadService.getLeadsByStatus(statusId);
        return new ResponseEntity<>(leads, HttpStatus.OK);
    }
    
    // Assign a lead to an employee
    @PostMapping("/{leadId}/assign/{employeeId}")
    public ResponseEntity<LeadAssignmentDTO> assignLead(@PathVariable Integer leadId, 
                                                      @PathVariable Integer employeeId,
                                                      @RequestParam Integer assignedByEmployeeId,
                                                      @RequestParam(required = false) String remarks) {
        LeadAssignmentDTO assignment = leadService.assignLead(leadId, employeeId, assignedByEmployeeId, remarks);
        if (assignment != null) {
            return new ResponseEntity<>(assignment, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // Reassign a lead to another employee (manager function)
    @PostMapping("/{leadId}/reassign/{newEmployeeId}")
    public ResponseEntity<LeadAssignmentDTO> reassignLead(@PathVariable Integer leadId, 
                                                        @PathVariable Integer newEmployeeId,
                                                        @RequestParam Integer managerEmployeeId,
                                                        @RequestParam(required = false) String remarks) {
        LeadAssignmentDTO assignment = leadService.reassignLead(leadId, newEmployeeId, managerEmployeeId, remarks);
        if (assignment != null) {
            return new ResponseEntity<>(assignment, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // Get leads assigned to an employee
    @GetMapping("/assigned-to/{employeeId}")
    public ResponseEntity<List<LeadDTO>> getLeadsAssignedToEmployee(@PathVariable Integer employeeId) {
        List<LeadDTO> leads = leadService.getLeadsAssignedToEmployee(employeeId);
        return new ResponseEntity<>(leads, HttpStatus.OK);
    }
    
    // Get assignment history for a lead
    @GetMapping("/{leadId}/assignment-history")
    public ResponseEntity<List<LeadAssignmentDTO>> getAssignmentHistory(@PathVariable Integer leadId) {
        List<LeadAssignmentDTO> history = leadService.getAssignmentHistory(leadId);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }
}
