package com.rocket.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeadAssignmentDTO {
    
    private Integer assignmentId;
    
    @NotNull(message = "Lead ID cannot be null")
    private Integer leadId;
    
    @NotNull(message = "Assigned to ID cannot be null")
    private Integer assignedToId;
    
    @NotNull(message = "Assigned by ID cannot be null")
    private Integer assignedById;
    
    private String remarks;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime assignedAt;
}
