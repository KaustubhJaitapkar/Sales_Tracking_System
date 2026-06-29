package com.rocket.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
@Table(name = "lead_sources")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeadSource {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "source_id")
    private Integer sourceId;
    
    @Column(name = "source_name", length = 100, nullable = false, unique = true)
    private String sourceName;
    
    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean isActive = true;
    
    @OneToMany(mappedBy = "source", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Lead> leads;
}

