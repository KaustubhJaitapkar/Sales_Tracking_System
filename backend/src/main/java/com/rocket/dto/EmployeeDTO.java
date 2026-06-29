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
public class EmployeeDTO {
    
    private Integer id;
    
    @NotBlank(message = "First name cannot be blank")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;
    
    @NotBlank(message = "Last name cannot be blank")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;
    
    @Size(max = 50, message = "Title cannot exceed 50 characters")
    private String title;
    
    @Email(message = "Email must be valid")
    @Size(max = 50, message = "Email cannot exceed 50 characters")
    private String email;
    
    @Size(max = 50, message = "Country cannot exceed 50 characters")
    private String country;
    
    @Size(max = 50, message = "City cannot exceed 50 characters")
    private String city;
    
    private Integer locationId;
}
