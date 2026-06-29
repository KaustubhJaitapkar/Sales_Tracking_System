package com.rocket.controller;

import com.rocket.dto.LeadStatusDTO;
import com.rocket.service.ILeadStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lead-statuses")
@RequiredArgsConstructor
public class LeadStatusController {
    
    private final ILeadStatusService leadStatusService;
    
    // Add a new lead status
    @PostMapping
    public ResponseEntity<LeadStatusDTO> addLeadStatus(@RequestBody LeadStatusDTO leadStatusDTO) {
        LeadStatusDTO savedStatus = leadStatusService.addLeadStatus(leadStatusDTO);
        return new ResponseEntity<>(savedStatus, HttpStatus.CREATED);
    }
    
    // Update a lead status
    @PutMapping("/{id}")
    public ResponseEntity<LeadStatusDTO> updateLeadStatus(@PathVariable Integer id, @RequestBody LeadStatusDTO leadStatusDetails) {
        LeadStatusDTO updatedStatus = leadStatusService.updateLeadStatus(id, leadStatusDetails);
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
    public ResponseEntity<LeadStatusDTO> getLeadStatusById(@PathVariable Integer id) {
        Optional<LeadStatusDTO> status = leadStatusService.getLeadStatusById(id);
        return status.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // Get all lead statuses
    @GetMapping
    public ResponseEntity<List<LeadStatusDTO>> getAllLeadStatuses() {
        List<LeadStatusDTO> statuses = leadStatusService.getAllLeadStatuses();
        return new ResponseEntity<>(statuses, HttpStatus.OK);
    }
    
    // Get lead status by name
    @GetMapping("/name/{statusName}")
    public ResponseEntity<LeadStatusDTO> getLeadStatusByName(@PathVariable String statusName) {
        Optional<LeadStatusDTO> status = leadStatusService.getLeadStatusByName(statusName);
        return status.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
