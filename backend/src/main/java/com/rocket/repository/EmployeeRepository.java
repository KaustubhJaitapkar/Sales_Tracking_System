package com.rocket.repository;

import com.rocket.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
    // search employees by first name
    List<Employee> findByFirstNameIgnoreCase(String firstName);

    // search employees by last name
    List<Employee> findByLastNameIgnoreCase(String lastName);

    // search employees by email
    Optional<Employee> findByEmailIgnoreCase(String email);

    // search employees by city
    List<Employee> findByCityIgnoreCase(String city);

    // search employees by title
    List<Employee> findByTitleIgnoreCase(String title);

    // search employees by country
    List<Employee> findByCountryIgnoreCase(String country);

}
