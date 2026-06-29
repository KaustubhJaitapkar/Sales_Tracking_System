package com.rocket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountDTO {
    
    private Integer userId;
    
    @NotNull(message = "Employee ID cannot be null")
    private Integer employeeId;
    
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;
    
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
    
    @NotBlank(message = "Account status cannot be blank")
    @Size(max = 20, message = "Account status cannot exceed 20 characters")
    private String accountStatus;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
}
