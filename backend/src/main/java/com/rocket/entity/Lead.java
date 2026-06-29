package com.rocket.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "leads")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lead_id")
    private Integer leadId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @NotNull(message = "Customer cannot be null")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_id", nullable = false)
    @NotNull(message = "Lead source cannot be null")
    private LeadSource source;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @NotNull(message = "Product cannot be null")
    private ProductDetails product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    @NotNull(message = "Created by employee cannot be null")
    private Employee createdBy;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean isActive = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    @NotNull(message = "Lead status cannot be null")
    private LeadStatus status;

    @Column(name = "description", columnDefinition = "TEXT")
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false)
    @NotNull(message = "Priority cannot be null")
    private LeadPriority priority = LeadPriority.Medium;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_to")
    private Employee assignedTo;

    @Column(name = "value", nullable = false)
    @Min(value = 0, message = "Lead value cannot be negative")
    private Integer value = 0;

    @JsonIgnore
    @OneToMany(
            mappedBy = "lead",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<LeadAssignment> assignments = new ArrayList<>();


    // ==========================
    // Utility Methods
    // ==========================

    public void addAssignment(LeadAssignment assignment) {
        assignments.add(assignment);
        assignment.setLead(this);
    }

    public void removeAssignment(LeadAssignment assignment) {
        assignments.remove(assignment);
        assignment.setLead(null);
    }

    public boolean isAssigned() {
        return assignedTo != null;
    }

    public boolean isHighPriority() {
        return LeadPriority.High.equals(priority);
    }

    public boolean isMediumPriority() {
        return LeadPriority.Medium.equals(priority);
    }

    public boolean isLowPriority() {
        return LeadPriority.Low.equals(priority);
    }

    public void assignLead(Employee employee) {
        this.assignedTo = employee;
    }

    public void updateStatus(LeadStatus status) {
        this.status = status;
    }

    public void updatePriority(LeadPriority priority) {
        this.priority = priority;
    }
}