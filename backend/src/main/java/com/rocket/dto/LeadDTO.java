package com.rocket.dto;

import com.rocket.entity.LeadPriority;
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
public class LeadDTO {
    
    private Integer leadId;
    
    @NotNull(message = "Customer ID cannot be null")
    private Integer customerId;
    
    @NotNull(message = "Source ID cannot be null")
    private Integer sourceId;
    
    @NotNull(message = "Product ID cannot be null")
    private Integer productId;
    
    @NotNull(message = "Created by (Employee ID) cannot be null")
    private Integer createdBy;
    
    @NotNull(message = "Status ID cannot be null")
    private Integer statusId;
    
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;
    
    @NotNull(message = "Priority cannot be null")
    private LeadPriority priority;
    
    private Integer assignedTo;
    
    @Min(value = 0, message = "Lead value cannot be negative")
    private Integer value;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
}
