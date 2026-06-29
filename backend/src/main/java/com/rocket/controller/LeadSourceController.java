package com.rocket.controller;

import com.rocket.dto.LeadSourceDTO;
import com.rocket.service.ILeadSourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lead-sources")
@RequiredArgsConstructor
public class LeadSourceController {
    
    private final ILeadSourceService leadSourceService;
    
    // Add a new lead source
    @PostMapping
    public ResponseEntity<LeadSourceDTO> addLeadSource(@RequestBody LeadSourceDTO leadSourceDTO) {
        LeadSourceDTO savedSource = leadSourceService.addLeadSource(leadSourceDTO);
        return new ResponseEntity<>(savedSource, HttpStatus.CREATED);
    }
    
    // Update a lead source
    @PutMapping("/{id}")
    public ResponseEntity<LeadSourceDTO> updateLeadSource(@PathVariable Integer id, @RequestBody LeadSourceDTO leadSourceDetails) {
        LeadSourceDTO updatedSource = leadSourceService.updateLeadSource(id, leadSourceDetails);
        if (updatedSource != null) {
            return new ResponseEntity<>(updatedSource, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // Delete a lead source
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeadSource(@PathVariable Integer id) {
        boolean deleted = leadSourceService.deleteLeadSource(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // Get lead source by ID
    @GetMapping("/{id}")
    public ResponseEntity<LeadSourceDTO> getLeadSourceById(@PathVariable Integer id) {
        Optional<LeadSourceDTO> source = leadSourceService.getLeadSourceById(id);
        return source.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // Get all lead sources
    @GetMapping
    public ResponseEntity<List<LeadSourceDTO>> getAllLeadSources() {
        List<LeadSourceDTO> sources = leadSourceService.getAllLeadSources();
        return new ResponseEntity<>(sources, HttpStatus.OK);
    }
    
    // Get lead source by name
    @GetMapping("/name/{sourceName}")
    public ResponseEntity<LeadSourceDTO> getLeadSourceByName(@PathVariable String sourceName) {
        Optional<LeadSourceDTO> source = leadSourceService.getLeadSourceByName(sourceName);
        return source.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
