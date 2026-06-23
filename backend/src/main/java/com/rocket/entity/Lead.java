package com.rocket.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "leads")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lead {
    
    @Id
    @Column(name = "lead_id")
    private Integer leadId;
    
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    
    @ManyToOne
    @JoinColumn(name = "source_id", nullable = false)
    private LeadSource source;
    
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductDetails product;
    
    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private Employee createdBy;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private LeadStatus status;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "priority", length = 10, nullable = false)
    private String priority;
    
    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private Employee assignedTo;
    
    @Column(name = "value", nullable = false)
    private Integer value;
    
    @OneToMany(mappedBy = "lead", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<LeadAssignment> assignments;
}

