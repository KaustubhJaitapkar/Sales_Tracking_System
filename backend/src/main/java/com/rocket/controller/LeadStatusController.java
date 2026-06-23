package com.rocket.controller;

import com.rocket.entity.LeadStatus;
import com.rocket.service.LeadStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lead-statuses")
public class LeadStatusController {
    
    @Autowired
    private LeadStatusService leadStatusService;
    
    // Add a new lead status
    @PostMapping
    public ResponseEntity<LeadStatus> addLeadStatus(@RequestBody LeadStatus leadStatus) {
        LeadStatus savedStatus = leadStatusService.addLeadStatus(leadStatus);
        return new ResponseEntity<>(savedStatus, HttpStatus.CREATED);
    }
    
    // Update a lead status
    @PutMapping("/{id}")
    public ResponseEntity<LeadStatus> updateLeadStatus(@PathVariable Integer id, @RequestBody LeadStatus leadStatusDetails) {
        LeadStatus updatedStatus = leadStatusService.updateLeadStatus(id, leadStatusDetails);
        if (updatedStatus != null) {
            return new ResponseEntity<>(updatedStatus, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // Delete a lead status
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeadStatus(@PathVariable Integer id) {
        boolean deleted = leadStatusService.deleteLeadStatus(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // Get lead status by ID
    @GetMapping("/{id}")
    public ResponseEntity<LeadStatus> getLeadStatusById(@PathVariable Integer id) {
        Optional<LeadStatus> status = leadStatusService.getLeadStatusById(id);
        return status.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // Get all lead statuses
    @GetMapping
    public ResponseEntity<List<LeadStatus>> getAllLeadStatuses() {
        List<LeadStatus> statuses = leadStatusService.getAllLeadStatuses();
        return new ResponseEntity<>(statuses, HttpStatus.OK);
    }
    
    // Get lead status by name
    @GetMapping("/name/{statusName}")
    public ResponseEntity<LeadStatus> getLeadStatusByName(@PathVariable String statusName) {
        Optional<LeadStatus> status = leadStatusService.getLeadStatusByName(statusName);
        return status.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
