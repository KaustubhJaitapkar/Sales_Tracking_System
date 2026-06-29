package com.rocket.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "first_name", length = 50)
    @NotBlank(message = "First name cannot be blank")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;
    
    @Column(name = "last_name", length = 50)
    @NotBlank(message = "Last name cannot be blank")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;
    
    @Column(name = "title", length = 50)
    @Size(max = 50, message = "Title cannot exceed 50 characters")
    private String title;
    
    @Column(name = "email", length = 50, unique = true)
    @Email(message = "Email must be valid")
    @Size(max = 50, message = "Email cannot exceed 50 characters")
    private String email;
    
    @Column(name = "country", length = 50)
    @Size(max = 50, message = "Country cannot exceed 50 characters")
    private String country;
    
    @Column(name = "city", length = 50)
    @Size(max = 50, message = "City cannot exceed 50 characters")
    private String city;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location")
    private Locations location;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean isActive = true;
    
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private UserAccount userAccount;
    
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Lead> createdLeads;
    
    @OneToMany(mappedBy = "assignedTo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Lead> assignedLeads;
    
    @OneToMany(mappedBy = "assignedTo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<LeadAssignment> assignedAssignments;
    
    @OneToMany(mappedBy = "assignedBy", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<LeadAssignment> createdAssignments;
}
