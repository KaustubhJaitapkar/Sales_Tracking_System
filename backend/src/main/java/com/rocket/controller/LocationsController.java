package com.rocket.controller;

import com.rocket.entity.Locations;
import com.rocket.service.LocationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/locations")
public class LocationsController {
    
    @Autowired
    private LocationsService locationsService;
    
    @PostMapping
    public ResponseEntity<Locations> addLocation(@RequestBody Locations location) {
        try {
            Locations savedLocation = locationsService.addLocation(location);
            return new ResponseEntity<>(savedLocation, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Locations> updateLocation(@PathVariable Integer id, @RequestBody Locations locationDetails) {
        Locations updatedLocation = locationsService.updateLocation(id, locationDetails);
        if (updatedLocation != null) {
            return new ResponseEntity<>(updatedLocation, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Integer id) {
        boolean deleted = locationsService.deleteLocation(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Locations> getLocationById(@PathVariable Integer id) {
        Optional<Locations> location = locationsService.getLocationById(id);
        return location.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    
    @GetMapping
    public ResponseEntity<List<Locations>> getAllLocationsUnpaged() {
        List<Locations> locations = locationsService.getAllLocations();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }
    
    @GetMapping("/city/{city}")
    public ResponseEntity<Locations> getLocationByCity(@PathVariable String city) {
        try {
            Optional<Locations> location = locationsService.getLocationByCity(city);
            return location.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                          .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/country/{country}")
    public ResponseEntity<Locations> getLocationByCountry(@PathVariable String country) {
        try {
            Optional<Locations> location = locationsService.getLocationByCountry(country);
            return location.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                          .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
