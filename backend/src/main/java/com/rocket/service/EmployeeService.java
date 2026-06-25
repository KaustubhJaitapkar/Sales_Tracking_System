package com.rocket.service;

import com.rocket.entity.Employee;
import com.rocket.repository.EmployeeRepository;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
    
    // Add a new employee
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    
    // Update an employee
    public Employee updateEmployee(Integer employeeId, Employee employeeDetails) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            if (employeeDetails.getFirstName() != null) {
                employee.setFirstName(employeeDetails.getFirstName());
            }
            if (employeeDetails.getLastName() != null) {
                employee.setLastName(employeeDetails.getLastName());
            }
            if (employeeDetails.getTitle() != null) {
                employee.setTitle(employeeDetails.getTitle());
            }
            if (employeeDetails.getEmail() != null) {
                employee.setEmail(employeeDetails.getEmail());
            }
            if (employeeDetails.getCountry() != null) {
                employee.setCountry(employeeDetails.getCountry());
            }
            if (employeeDetails.getCity() != null) {
                employee.setCity(employeeDetails.getCity());
            }
            if (employeeDetails.getLocation() != null) {
                employee.setLocation(employeeDetails.getLocation());
            }
            return employeeRepository.save(employee);
        }
        throw new RuntimeException("Employee not found with id: " + employeeId);
    }
    
    // Delete an employee
    public boolean deleteEmployee(Integer employeeId) {
        if (employeeRepository.existsById(employeeId)) {
            employeeRepository.deleteById(employeeId);
            return true;
        }
        throw new RuntimeException("Employee not found with id: " + employeeId);
    }
    
    // Get employee by ID
    public Optional<Employee> getEmployeeById(Integer employeeId) {
        return employeeRepository.findById(employeeId);
    }
    
    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> getEmployeesByFirstNameIgnoreCase(String firstName) {
        return employeeRepository.findByFirstNameIgnoreCase(firstName);
    }

    public List<Employee> getEmployeesByLastNameIgnoreCase(String lastName) {
        return employeeRepository.findByLastNameIgnoreCase(lastName);
    }

    public List<Employee> getEmployeesByCityIgnoreCase(String city) {
        return employeeRepository.findByCityIgnoreCase(city);
    }

    public List<Employee> getEmployeesByTitleIgnoreCase(String title) {
        return employeeRepository.findByTitleIgnoreCase(title);
    }

    public List<Employee> getEmployeesByCountryIgnoreCase(String country) {
        return employeeRepository.findByCountryIgnoreCase(country);
    }
    
    // Get employee by email
    public Optional<Employee> getEmployeeByEmailIgnoreCase(String email) {
        return employeeRepository.findByEmailIgnoreCase(email);
    }

    public List<Employee> searchEmployees(String firstName, String lastName, String city, String title, String country) {
        return employeeRepository.findAll().stream()
                .filter(e -> firstName == null || e.getFirstName().equalsIgnoreCase(firstName))
                .filter(e -> lastName == null || e.getLastName().equalsIgnoreCase(lastName))
                .filter(e -> city == null || e.getCity().equalsIgnoreCase(city))
                .filter(e -> title == null || e.getTitle().equalsIgnoreCase(title))
                .filter(e -> country == null || e.getCountry().equalsIgnoreCase(country))
                .toList();
    }
}
