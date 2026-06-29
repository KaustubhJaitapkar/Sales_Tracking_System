package com.rocket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationsDTO {
    
    private Integer id;
    
    @Size(max = 50, message = "Phone cannot exceed 50 characters")
    private String phone;
    
    @Size(max = 250, message = "Street cannot exceed 250 characters")
    private String street;
    
    @Size(max = 50, message = "Country cannot exceed 50 characters")
    private String country;
    
    @Size(max = 50, message = "City cannot exceed 50 characters")
    private String city;
}
