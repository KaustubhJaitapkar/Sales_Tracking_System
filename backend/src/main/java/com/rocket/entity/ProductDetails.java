package com.rocket.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
@Table(name = "product_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;
    
    @Column(name = "product_name", length = 100, nullable = false)
    private String productName;
    
    @Column(name = "product_type", length = 100, nullable = false)
    private String productType;
    
    @Column(name = "product_status", length = 100, nullable = false)
    private String productStatus;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean isActive = true;
    
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<Lead> leads;
}
