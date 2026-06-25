package com.rocket.controller;

import com.rocket.entity.Lead;
import com.rocket.entity.LeadAssignment;
import com.rocket.entity.LeadPriority;
import com.rocket.service.LeadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    @Autowired
    private LeadService leadService;

    /**
     * Create Lead
     */
    @PostMapping
    public ResponseEntity<Lead> addLead(
            @RequestBody Lead lead) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(leadService.addLead(lead));
    }

    /**
     * Update Lead
     */
    @PutMapping("/{leadId}")
    public ResponseEntity<Lead> updateLead(
            @PathVariable Integer leadId,
            @RequestBody Lead leadDetails) {

        return ResponseEntity.ok(
                leadService.updateLead(
                        leadId,
                        leadDetails
                )
        );
    }

    /**
     * Delete Lead
     */
    @DeleteMapping("/{leadId}")
    public ResponseEntity<String> deleteLead(
            @PathVariable Integer leadId) {

        leadService.deleteLead(leadId);

        return ResponseEntity.ok(
                "Lead deleted successfully."
        );
    }

    /**
     * Get Lead By Id
     */
    @GetMapping("/{leadId}")
    public ResponseEntity<Lead> getLeadById(
            @PathVariable Integer leadId) {

        return ResponseEntity.ok(
                leadService.getLeadById(leadId)
        );
    }

    /**
     * Get All Leads
     */
    @GetMapping
    public ResponseEntity<List<Lead>> getAllLeads() {

        return ResponseEntity.ok(
                leadService.getAllLeads()
        );
    }

    /**
     * Get Leads By Product
     */
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Lead>> getLeadsByProduct(
            @PathVariable Integer productId) {

        return ResponseEntity.ok(
                leadService.getLeadsByProduct(productId)
        );
    }

    /**
     * Get Leads By Source
     */
    @GetMapping("/source/{sourceId}")
    public ResponseEntity<List<Lead>> getLeadsBySource(
            @PathVariable Integer sourceId) {

        return ResponseEntity.ok(
                leadService.getLeadsBySource(sourceId)
        );
    }

    /**
     * Get Leads By Status
     */
    @GetMapping("/status/{statusId}")
    public ResponseEntity<List<Lead>> getLeadsByStatus(
            @PathVariable Integer statusId) {

        return ResponseEntity.ok(
                leadService.getLeadsByStatus(statusId)
        );
    }

    /**
     * Get Leads By Priority
     */
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Lead>> getLeadsByPriority(
            @PathVariable LeadPriority priority) {

        return ResponseEntity.ok(
                leadService.getLeadsByPriority(priority)
        );
    }

    /**
     * Get Leads Assigned To Employee
     */
    @GetMapping("/assigned-to/{employeeId}")
    public ResponseEntity<List<Lead>> getLeadsAssignedToEmployee(
            @PathVariable Integer employeeId) {

        return ResponseEntity.ok(
                leadService.getLeadsAssignedToEmployee(employeeId)
        );
    }

    /**
     * Get Leads Created By Employee
     */
    @GetMapping("/created-by/{employeeId}")
    public ResponseEntity<List<Lead>> getLeadsCreatedByEmployee(
            @PathVariable Integer employeeId) {

        return ResponseEntity.ok(
                leadService.getLeadsCreatedByEmployee(employeeId)
        );
    }

    /**
     * Get Customer Leads
     */
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Lead>> getLeadsByCustomer(
            @PathVariable Integer customerId) {

        return ResponseEntity.ok(
                leadService.getLeadsByCustomer(customerId)
        );
    }

    /**
     * Get Unassigned Leads
     */
    @GetMapping("/unassigned")
    public ResponseEntity<List<Lead>> getUnassignedLeads() {

        return ResponseEntity.ok(
                leadService.getUnassignedLeads()
        );
    }

    /**
     * Get Assigned Leads
     */
    @GetMapping("/assigned")
    public ResponseEntity<List<Lead>> getAssignedLeads() {

        return ResponseEntity.ok(
                leadService.getAssignedLeads()
        );
    }

    /**
     * Get High Value Leads
     */
    @GetMapping("/high-value/{value}")
    public ResponseEntity<List<Lead>> getHighValueLeads(
            @PathVariable Integer value) {

        return ResponseEntity.ok(
                leadService.getHighValueLeads(value)
        );
    }

    /**
     * Assign Lead
     */
    @PostMapping("/{leadId}/assign/{employeeId}")
    public ResponseEntity<LeadAssignment> assignLead(
            @PathVariable Integer leadId,
            @PathVariable Integer employeeId,
            @RequestParam Integer assignedByEmployeeId,
            @RequestParam(required = false) String remarks) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        leadService.assignLead(
                                leadId,
                                employeeId,
                                assignedByEmployeeId,
                                remarks
                        )
                );
    }

    /**
     * Reassign Lead
     */
    @PostMapping("/{leadId}/reassign/{employeeId}")
    public ResponseEntity<LeadAssignment> reassignLead(
            @PathVariable Integer leadId,
            @PathVariable Integer employeeId,
            @RequestParam Integer managerEmployeeId,
            @RequestParam(required = false) String remarks) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        leadService.reassignLead(
                                leadId,
                                employeeId,
                                managerEmployeeId,
                                remarks
                        )
                );
    }

    /**
     * Assignment History
     */
    @GetMapping("/{leadId}/assignment-history")
    public ResponseEntity<List<LeadAssignment>> getAssignmentHistory(
            @PathVariable Integer leadId) {

        return ResponseEntity.ok(
                leadService.getAssignmentHistory(leadId)
        );
    }

    /**
     * Debug Endpoint
     */
    @GetMapping("/debug/print")
    public ResponseEntity<String> printAllLeads() {

        leadService.printAllLeads();

        return ResponseEntity.ok(
                "Lead data printed in console."
        );
    }
}