package com.rocket.controller;

import com.rocket.dto.LocationsDTO;
import com.rocket.service.ILocationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationsController {
    
    private final ILocationsService locationsService;
    
    @PostMapping
    public ResponseEntity<LocationsDTO> addLocation(@RequestBody LocationsDTO locationDTO) {
        try {
            LocationsDTO savedLocation = locationsService.addLocation(locationDTO);
            return new ResponseEntity<>(savedLocation, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<LocationsDTO> updateLocation(@PathVariable Integer id, @RequestBody LocationsDTO locationDetails) {
        LocationsDTO updatedLocation = locationsService.updateLocation(id, locationDetails);
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
    public ResponseEntity<LocationsDTO> getLocationById(@PathVariable Integer id) {
        Optional<LocationsDTO> location = locationsService.getLocationById(id);
        return location.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<LocationsDTO>> getAllLocationsUnpaged() {
        List<LocationsDTO> locations = locationsService.getAllLocations();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }
    
    @GetMapping("/city/{city}")
    public ResponseEntity<List<LocationsDTO>> getLocationByCity(@PathVariable String city) {
        try {
            List<LocationsDTO> locations = locationsService.getLocationByCity(city);
            if (locations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(locations, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/country/{country}")
    public ResponseEntity<List<LocationsDTO>> getLocationByCountry(@PathVariable String country) {
        try {
            List<LocationsDTO> locations = locationsService.getLocationByCountry(country);
            if (locations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(locations, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
