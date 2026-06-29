package com.rocket.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
@Table(name = "Locations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Locations {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "phone", length = 50)
    private String phone;
    
    @Column(name = "street", length = 250)
    private String street;
    
    @Column(name = "country", length = 50)
    private String country;
    
    @Column(name = "city", length = 50)
    private String city;
    
    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean isActive = true;
    
    @OneToMany(mappedBy = "location")
    @JsonIgnore
    private List<Employee> employees;
}
