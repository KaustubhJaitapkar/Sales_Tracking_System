package com.rocket.controller;

import com.rocket.dto.EmployeeDTO;
import com.rocket.dto.LeadDTO;
import com.rocket.service.IEmployeeEntityService;
import com.rocket.service.ILeadEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    
    private final IEmployeeEntityService employeeService;
    private final ILeadEntityService leadService;
    
    // Add a new employee
    @PostMapping
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO savedEmployee = employeeService.addEmployee(employeeDTO);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
    
    // Update an employee
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeDTO employeeDetails) {
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
        if (updatedEmployee != null) {
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // Delete an employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        boolean deleted = employeeService.deleteEmployee(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // Get employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Integer id) {
        Optional<EmployeeDTO> employee = employeeService.getEmployeeById(id);
        return employee.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // Get all employees
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    
    // Get employee by email
    @GetMapping("/email/{email}")
    public ResponseEntity<EmployeeDTO> getEmployeeByEmail(@PathVariable String email) {
        Optional<EmployeeDTO> employee = employeeService.getEmployeeByEmail(email);
        return employee.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // Search by first name
    @GetMapping("/search/firstName/{firstName}")
    public ResponseEntity<List<EmployeeDTO>> getByFirstName(@PathVariable String firstName) {
        List<EmployeeDTO> employees = employeeService.getEmployeesByFirstNameIgnoreCase(firstName);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // Search by last name
    @GetMapping("/search/lastName/{lastName}")
    public ResponseEntity<List<EmployeeDTO>> getByLastName(@PathVariable String lastName) {
        List<EmployeeDTO> employees = employeeService.getEmployeesByLastNameIgnoreCase(lastName);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // Search by city
    @GetMapping("/search/city/{city}")
    public ResponseEntity<List<EmployeeDTO>> getByCity(@PathVariable String city) {
        List<EmployeeDTO> employees = employeeService.getEmployeesByCityIgnoreCase(city);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // Search by title
    @GetMapping("/search/title/{title}")
    public ResponseEntity<List<EmployeeDTO>> getByTitle(@PathVariable String title) {
        List<EmployeeDTO> employees = employeeService.getEmployeesByTitleIgnoreCase(title);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // Search by country
    @GetMapping("/search/country/{country}")
    public ResponseEntity<List<EmployeeDTO>> getByCountry(@PathVariable String country) {
        List<EmployeeDTO> employees = employeeService.getEmployeesByCountryIgnoreCase(country);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // Get leads by employee
    @GetMapping("/{id}/leads")
    public ResponseEntity<List<LeadDTO>> getLeadsByEmployee(@PathVariable Integer id) {
        List<LeadDTO> leads = leadService.getLeadsAssignedToEmployee(id);
        return new ResponseEntity<>(leads, HttpStatus.OK);
    }

    // Search employees with multiple filters
    @GetMapping("/search")
    public ResponseEntity<List<EmployeeDTO>> searchEmployees(
        @RequestParam(required = false) String firstName,
        @RequestParam(required = false) String lastName,
        @RequestParam(required = false) String city,
        @RequestParam(required = false) String title,
        @RequestParam(required = false) String country
    ) {
        List<EmployeeDTO> employees = employeeService.searchEmployees(firstName, lastName, city, title, country);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}
