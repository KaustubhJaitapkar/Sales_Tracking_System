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
public class LeadStatusDTO {
    
    private Integer statusId;
    
    @NotBlank(message = "Status name cannot be blank")
    @Size(max = 50, message = "Status name cannot exceed 50 characters")
    private String statusName;
}
