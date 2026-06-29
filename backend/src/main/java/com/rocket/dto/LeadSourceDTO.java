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
public class LeadSourceDTO {
    
    private Integer sourceId;
    
    @NotBlank(message = "Source name cannot be blank")
    @Size(max = 100, message = "Source name cannot exceed 100 characters")
    private String sourceName;
}
