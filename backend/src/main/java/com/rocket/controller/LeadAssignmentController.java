package com.rocket.controller;

import com.rocket.entity.LeadAssignment;
import com.rocket.service.LeadAssignmentService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lead-assignments")
@CrossOrigin(origins = "*")
public class LeadAssignmentController {

    private final LeadAssignmentService leadAssignmentService;

    public LeadAssignmentController(
            LeadAssignmentService leadAssignmentService) {

        this.leadAssignmentService = leadAssignmentService;
    }

    /**
     * Assign Lead
     */
    @PostMapping("/assign")
    public ResponseEntity<LeadAssignment> assignLead(
            @RequestParam Integer leadId,
            @RequestParam Integer assignedToId,
            @RequestParam Integer assignedById,
            @RequestParam(required = false) String remarks) {

        LeadAssignment assignment =
                leadAssignmentService.assignLead(
                        leadId,
                        assignedToId,
                        assignedById,
                        remarks);

        return ResponseEntity.ok(assignment);
    }

    /**
     * Reassign Lead
     */
    @PostMapping("/reassign")
    public ResponseEntity<LeadAssignment> reassignLead(
            @RequestParam Integer leadId,
            @RequestParam Integer assignedToId,
            @RequestParam Integer assignedById,
            @RequestParam(required = false) String remarks) {

        LeadAssignment assignment =
                leadAssignmentService.reassignLead(
                        leadId,
                        assignedToId,
                        assignedById,
                        remarks);

        return ResponseEntity.ok(assignment);
    }

    /**
     * Assignment History of Lead
     */
    @GetMapping("/lead/{leadId}")
    public ResponseEntity<List<LeadAssignment>> getLeadAssignmentHistory(
            @PathVariable Integer leadId) {

        return ResponseEntity.ok(
                leadAssignmentService.getLeadAssignmentHistory(leadId));
    }

    /**
     * Current Assignment of Lead
     */
    @GetMapping("/lead/{leadId}/current")
    public ResponseEntity<LeadAssignment> getCurrentAssignment(
            @PathVariable Integer leadId) {

        return ResponseEntity.ok(
                leadAssignmentService.getCurrentAssignment(leadId));
    }

    /**
     * Assignments Received by Employee
     */
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<LeadAssignment>> getAssignmentsByEmployee(
            @PathVariable Integer employeeId) {

        return ResponseEntity.ok(
                leadAssignmentService.getAssignmentsByEmployee(employeeId));
    }

    /**
     * Assignments Made By Manager
     */
    @GetMapping("/manager/{managerId}")
    public ResponseEntity<List<LeadAssignment>> getAssignmentsByManager(
            @PathVariable Integer managerId) {

        return ResponseEntity.ok(
                leadAssignmentService.getAssignmentsByManager(managerId));
    }

    /**
     * Check if Lead has Assignment History
     */
    @GetMapping("/lead/{leadId}/exists")
    public ResponseEntity<Boolean> isLeadAssigned(
            @PathVariable Integer leadId) {

        return ResponseEntity.ok(
                leadAssignmentService.isLeadAssigned(leadId));
    }
}