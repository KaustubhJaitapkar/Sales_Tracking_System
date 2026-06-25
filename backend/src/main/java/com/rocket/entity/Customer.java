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
@Table(name = "customer_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;
    
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;
    
    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;
    
    @Column(name = "email", length = 100, nullable = false, unique = true)
    @Email
    private String email;
    
    @Column(name = "phone_no", length = 15, nullable = false, unique = true)
    private String phoneNo;
    
    @Column(name = "organization_name", length = 50)
    private String organizationName;
    
    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Lead> leads;
}

