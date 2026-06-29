package com.rocket.service;

import com.rocket.dto.EmployeeDTO;
import java.util.List;
import java.util.Optional;

public interface IEmployeeEntityService {
    
    EmployeeDTO addEmployee(EmployeeDTO employeeDTO);
    
    EmployeeDTO updateEmployee(Integer employeeId, EmployeeDTO employeeDetails);
    
    boolean deleteEmployee(Integer employeeId);
    
    Optional<EmployeeDTO> getEmployeeById(Integer employeeId);
    
    List<EmployeeDTO> getAllEmployees();
    
    Optional<EmployeeDTO> getEmployeeByEmail(String email);
    
    List<EmployeeDTO> getEmployeesByFirstNameIgnoreCase(String firstName);
    
    List<EmployeeDTO> getEmployeesByLastNameIgnoreCase(String lastName);
    
    List<EmployeeDTO> getEmployeesByCityIgnoreCase(String city);
    
    List<EmployeeDTO> getEmployeesByTitleIgnoreCase(String title);
    
    List<EmployeeDTO> getEmployeesByCountryIgnoreCase(String country);
    
    Optional<EmployeeDTO> getEmployeeByEmailIgnoreCase(String email);
    
    List<EmployeeDTO> searchEmployees(String firstName, String lastName, String city, String title, String country);
}
