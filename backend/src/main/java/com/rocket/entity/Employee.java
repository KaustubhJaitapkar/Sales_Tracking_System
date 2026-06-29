package com.rocket.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;
    
    @Column(name = "last_name", length = 50)
    private String lastName;
    
    @Column(name = "title", length = 50)
    private String title;
    
    @Column(name = "email", length = 50, unique = true)
    @Email
    private String email;
    
    @Column(name = "country", length = 50)
    private String country;
    
    @Column(name = "city", length = 50)
    private String city;
    
    @ManyToOne
    @JoinColumn(name = "location")
    private Locations location;
    
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private UserAccount userAccount;
    
    @OneToMany(mappedBy = "createdBy")
    @JsonIgnore
    private List<Lead> createdLeads;
    
    @OneToMany(mappedBy = "assignedTo")
    @JsonIgnore
    private List<Lead> assignedLeads;
    
    @OneToMany(mappedBy = "assignedTo")
    @JsonIgnore
    private List<LeadAssignment> assignedAssignments;
    
    @OneToMany(mappedBy = "assignedBy")
    @JsonIgnore
    private List<LeadAssignment> createdAssignments;
}
