package com.rocket.controller;

import com.rocket.entity.LeadSource;
import com.rocket.service.LeadSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lead-sources")
public class LeadSourceController {
    
    @Autowired
    private LeadSourceService leadSourceService;
    
    // Add a new lead source
    @PostMapping
    public ResponseEntity<LeadSource> addLeadSource(@RequestBody LeadSource leadSource) {
        LeadSource savedSource = leadSourceService.addLeadSource(leadSource);
        return new ResponseEntity<>(savedSource, HttpStatus.CREATED);
    }
    
    // Update a lead source
    @PutMapping("/{id}")
    public ResponseEntity<LeadSource> updateLeadSource(@PathVariable Integer id, @RequestBody LeadSource leadSourceDetails) {
        LeadSource updatedSource = leadSourceService.updateLeadSource(id, leadSourceDetails);
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
    public ResponseEntity<LeadSource> getLeadSourceById(@PathVariable Integer id) {
        Optional<LeadSource> source = leadSourceService.getLeadSourceById(id);
        return source.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // Get all lead sources
    @GetMapping
    public ResponseEntity<List<LeadSource>> getAllLeadSources() {
        List<LeadSource> sources = leadSourceService.getAllLeadSources();
        return new ResponseEntity<>(sources, HttpStatus.OK);
    }
    
    // Get lead source by name
    @GetMapping("/name/{sourceName}")
    public ResponseEntity<LeadSource> getLeadSourceByName(@PathVariable String sourceName) {
        Optional<LeadSource> source = leadSourceService.getLeadSourceByName(sourceName);
        return source.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
