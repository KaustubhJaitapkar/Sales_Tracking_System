package com.rocket.service;

import com.rocket.dto.EmployeeDTO;
import java.util.List;

public interface IEmployeeService {
    
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
    
    EmployeeDTO updateEmployee(Integer employeeId, EmployeeDTO employeeDTO);
    
    void deleteEmployee(Integer employeeId);
    
    EmployeeDTO getEmployeeById(Integer employeeId);
    
    List<EmployeeDTO> getAllEmployees();
    
    EmployeeDTO getEmployeeByEmail(String email);
    
    List<EmployeeDTO> getEmployeesByLocation(Integer locationId);
    
    List<EmployeeDTO> getEmployeesByTitle(String title);
    
    List<EmployeeDTO> searchEmployees(String searchTerm);
    
    boolean employeeExists(Integer employeeId);
}
