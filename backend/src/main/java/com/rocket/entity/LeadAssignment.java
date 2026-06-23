package com.rocket.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;

@Entity
@Table(name = "lead_assignment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeadAssignment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id")
    private Integer assignmentId;
    
    @ManyToOne
    @JoinColumn(name = "lead_id", nullable = false)
    private Lead lead;
    
    @ManyToOne
    @JoinColumn(name = "assigned_to", nullable = false)
    private Employee assignedTo;
    
    @ManyToOne
    @JoinColumn(name = "assigned_by", nullable = false)
    private Employee assignedBy;
    
    @Column(name = "assigned_at")
    private LocalDateTime assignedAt;
    
    @Column(name = "remarks", columnDefinition = "TEXT")
    private String remarks;
}
