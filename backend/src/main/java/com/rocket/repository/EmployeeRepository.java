package com.rocket.repository;

import com.rocket.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
    @Query("SELECT e FROM Employee e WHERE e.email = ?1 AND e.isActive = true")
    Optional<Employee> findByEmail(String email);
    
    @Query("SELECT e FROM Employee e WHERE e.isActive = true")
    List<Employee> findAllActive();
    
    @Query("SELECT e FROM Employee e WHERE e.id = ?1 AND e.isActive = true")
    Optional<Employee> findByIdActive(Integer id);
    
    @Query("SELECT e FROM Employee e WHERE LOWER(e.firstName) = LOWER(?1) AND e.isActive = true")
    List<Employee> findByFirstNameIgnoreCase(String firstName);
    
    @Query("SELECT e FROM Employee e WHERE LOWER(e.lastName) = LOWER(?1) AND e.isActive = true")
    List<Employee> findByLastNameIgnoreCase(String lastName);
    
    @Query("SELECT e FROM Employee e WHERE LOWER(e.city) = LOWER(?1) AND e.isActive = true")
    List<Employee> findByCityIgnoreCase(String city);
    
    @Query("SELECT e FROM Employee e WHERE LOWER(e.title) = LOWER(?1) AND e.isActive = true")
    List<Employee> findByTitleIgnoreCase(String title);
    
    @Query("SELECT e FROM Employee e WHERE LOWER(e.country) = LOWER(?1) AND e.isActive = true")
    List<Employee> findByCountryIgnoreCase(String country);
}
