package com.rocket.service;

import com.rocket.dto.LocationsDTO;
import java.util.List;
import java.util.Optional;

public interface ILocationsService {
    
    LocationsDTO addLocation(LocationsDTO locationDTO);
    
    LocationsDTO updateLocation(Integer locationId, LocationsDTO locationDetails);
    
    boolean deleteLocation(Integer locationId);
    
    Optional<LocationsDTO> getLocationById(Integer locationId);
    
    List<LocationsDTO> getAllLocations();
    
    List<LocationsDTO> getLocationByCity(String city);
    
    List<LocationsDTO> getLocationByCountry(String country);
}
