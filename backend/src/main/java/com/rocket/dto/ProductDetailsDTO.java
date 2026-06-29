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
public class ProductDetailsDTO {
    
    private Integer productId;
    
    @NotBlank(message = "Product name cannot be blank")
    @Size(max = 100, message = "Product name cannot exceed 100 characters")
    private String productName;
    
    @NotBlank(message = "Product type cannot be blank")
    @Size(max = 100, message = "Product type cannot exceed 100 characters")
    private String productType;
    
    @NotBlank(message = "Product status cannot be blank")
    @Size(max = 100, message = "Product status cannot exceed 100 characters")
    private String productStatus;
    
    private String description;
}
