package com.rocket.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAccount {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false, unique = true)
    @NotNull(message = "Employee cannot be null")
    private Employee employee;
    
    @Column(name = "username", length = 50, nullable = false, unique = true)
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;
    
    @Column(name = "password_hash", length = 255, nullable = false)
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @JsonIgnore
    private String passwordHash;
    
    @Column(name = "account_status", length = 20, nullable = false)
    @NotBlank(message = "Account status cannot be blank")
    @Pattern(regexp = "ACTIVE|INACTIVE|SUSPENDED", message = "Account status must be one of: ACTIVE, INACTIVE, SUSPENDED")
    private String accountStatus;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean isActive = true;
}
