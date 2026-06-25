package com.rocket.service;

import com.rocket.entity.Locations;
import com.rocket.repository.LocationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LocationsService {
    
    @Autowired
    private LocationsRepository locationsRepository;
    
    // Add a new location
    public Locations addLocation(Locations location) {
        if (location == null) {
            throw new IllegalArgumentException("Location cannot be null");
        }
        return locationsRepository.save(location);
    }
    
    // Update a location
    public Locations updateLocation(Integer locationId, Locations locationDetails) {
        if (locationId == null) {
            throw new IllegalArgumentException("Location ID cannot be null");
        }
        Optional<Locations> optionalLocation = locationsRepository.findById(locationId);
        if (optionalLocation.isPresent()) {
            Locations location = optionalLocation.get();
            if (locationDetails.getPhone() != null) {
                location.setPhone(locationDetails.getPhone());
            }
            if (locationDetails.getStreet() != null) {
                location.setStreet(locationDetails.getStreet());
            }
            if (locationDetails.getCity() != null) {
                location.setCity(locationDetails.getCity());
            }
            if (locationDetails.getCountry() != null) {
                location.setCountry(locationDetails.getCountry());
            }
            return locationsRepository.save(location);
        }
        return null;
    }
    
    // Delete a location
    public boolean deleteLocation(Integer locationId) {
        if (locationId == null) {
            throw new IllegalArgumentException("Location ID cannot be null");
        }
        if (locationsRepository.existsById(locationId)) {
            locationsRepository.deleteById(locationId);
            return true;
        }
        return false;
    }
    
    // Get location by ID
    public Optional<Locations> getLocationById(Integer locationId) {
        if (locationId == null) {
            throw new IllegalArgumentException("Location ID cannot be null");
        }
        return locationsRepository.findById(locationId);
    }
    
    // Get all locations
    public List<Locations> getAllLocations() {
        return locationsRepository.findAll();
    }
    
    // Get location by city
    public Optional<Locations> getLocationByCity(String city) {
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("City cannot be null or empty");
        }
        return locationsRepository.findByCity(city);
    }
    
    // Get location by country
    public Optional<Locations> getLocationByCountry(String country) {
        if (country == null || country.trim().isEmpty()) {
            throw new IllegalArgumentException("Country cannot be null or empty");
        }
        return locationsRepository.findByCountry(country);
    }
}
