package com.rocket.service.impl;

import com.rocket.dto.LocationsDTO;
import com.rocket.entity.Locations;
import com.rocket.mapper.EntityDTOMapper;
import com.rocket.repository.LocationsRepository;
import com.rocket.service.ILocationsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LocationsServiceImpl implements ILocationsService {
    
    private final LocationsRepository locationsRepository;
    private final EntityDTOMapper mapper;
    
    @Override
    public LocationsDTO addLocation(LocationsDTO locationDTO) {
        log.info("Adding new location in city: {}", locationDTO.getCity());
        if (locationDTO == null) {
            throw new IllegalArgumentException("Location cannot be null");
        }
        Locations location = mapper.toLocationsEntity(locationDTO);
        Locations saved = locationsRepository.save(location);
        return mapper.toLocationsDTO(saved);
    }
    
    @Override
    public LocationsDTO updateLocation(Integer locationId, LocationsDTO locationDetails) {
        log.info("Updating location with ID: {}", locationId);
        if (locationId == null) {
            throw new IllegalArgumentException("Location ID cannot be null");
        }
        Optional<Locations> optionalLocation = locationsRepository.findByIdActive(locationId);
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
            Locations updated = locationsRepository.save(location);
            return mapper.toLocationsDTO(updated);
        }
        return null;
    }
    
    @Override
    public boolean deleteLocation(Integer locationId) {
        log.info("Deleting location with ID: {}", locationId);
        if (locationId == null) {
            throw new IllegalArgumentException("Location ID cannot be null");
        }
        Optional<Locations> optionalLocation = locationsRepository.findByIdActive(locationId);
        if (optionalLocation.isPresent()) {
            Locations location = optionalLocation.get();
            location.setIsActive(false);
            locationsRepository.save(location);
            return true;
        }
        return false;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<LocationsDTO> getLocationById(Integer locationId) {
        log.info("Fetching location with ID: {}", locationId);
        if (locationId == null) {
            throw new IllegalArgumentException("Location ID cannot be null");
        }
        return locationsRepository.findByIdActive(locationId)
                .map(mapper::toLocationsDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LocationsDTO> getAllLocations() {
        log.info("Fetching all locations");
        return locationsRepository.findAllActive()
                .stream()
                .map(mapper::toLocationsDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LocationsDTO> getLocationByCity(String city) {
        log.info("Fetching locations by city: {}", city);
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("City cannot be null or empty");
        }
        return locationsRepository.findByCity(city)
                .stream()
                .map(mapper::toLocationsDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LocationsDTO> getLocationByCountry(String country) {
        log.info("Fetching locations by country: {}", country);
        if (country == null || country.trim().isEmpty()) {
            throw new IllegalArgumentException("Country cannot be null or empty");
        }
        return locationsRepository.findByCountry(country)
                .stream()
                .map(mapper::toLocationsDTO)
                .collect(Collectors.toList());
    }
}
