package com.rocket.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
@Table(name = "lead_status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeadStatus {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private Integer statusId;
    
    @Column(name = "status_name", length = 50, nullable = false, unique = true)
    private String statusName;
    
    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean isActive = true;
    
    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Lead> leads;
}
