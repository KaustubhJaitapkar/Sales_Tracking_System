package com.rocket.controller;

import com.rocket.entity.Employee;
import com.rocket.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //Create employee
    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    //Get all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    //Get employee by ID
    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    //Update employee
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Integer id,
                                   @RequestBody Employee employeeDetails) {
        return employeeService.updateEmployee(id, employeeDetails);
    }

    //Delete employee
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return "Employee deleted successfully";
    }

    //Search by first name
    @GetMapping("/search/firstName/{firstName}")
    public List<Employee> getByFirstName(@PathVariable String firstName) {
        return employeeService.getEmployeesByFirstNameIgnoreCase(firstName);
    }

    //Search by last name
    @GetMapping("/search/lastName/{lastName}")
    public List<Employee> getByLastName(@PathVariable String lastName) {
        return employeeService.getEmployeesByLastNameIgnoreCase(lastName);
    }

    //Search by city
    @GetMapping("/search/city/{city}")
    public List<Employee> getByCity(@PathVariable String city) {
        return employeeService.getEmployeesByCityIgnoreCase(city);
    }

    //Search by title
    @GetMapping("/search/title/{title}")
    public List<Employee> getByTitle(@PathVariable String title) {
        return employeeService.getEmployeesByTitleIgnoreCase(title);
    }

    //Search by country
    @GetMapping("/search/country/{country}")
    public List<Employee> getByCountry(@PathVariable String country) {
        return employeeService.getEmployeesByCountryIgnoreCase(country);
    }

    //Search by email
    @GetMapping("/search/email/{email}")
    public Optional<Employee> getByEmail(@PathVariable String email) {
        return employeeService.getEmployeeByEmailIgnoreCase(email);
    }

    @Autowired
    private LeadService leadService;
    @GetMapping("/{id}/leads")
    public List<Lead> getLeadsByEmployee(@PathVariable int id){
        return leadService.getLeadsByEmployee(id);
    }

    @GetMapping("/search")
    public List<Employee> searchEmployees(
        @RequestParam(required = false) String firstName,
        @RequestParam(required = false) String lastName,
        @RequestParam(required = false) String city,
        @RequestParam(required = false) String title,
        @RequestParam(required = false) String country
    ) {
        return employeeService.searchEmployees(firstName, lastName, city, title, country);
    }
}