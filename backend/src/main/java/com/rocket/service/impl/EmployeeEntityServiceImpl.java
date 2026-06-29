package com.rocket.service.impl;

import com.rocket.dto.EmployeeDTO;
import com.rocket.entity.Employee;
import com.rocket.mapper.EntityDTOMapper;
import com.rocket.repository.EmployeeRepository;
import com.rocket.service.IEmployeeEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class EmployeeEntityServiceImpl implements IEmployeeEntityService {
    
    private final EmployeeRepository employeeRepository;
    private final EntityDTOMapper mapper;
    
    @Override
    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        log.info("Adding new employee: {} {}", employeeDTO.getFirstName(), employeeDTO.getLastName());
        Employee employee = mapper.toEmployeeEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return mapper.toEmployeeDTO(savedEmployee);
    }
    
    @Override
    public EmployeeDTO updateEmployee(Integer employeeId, EmployeeDTO employeeDetails) {
        log.info("Updating employee with ID: {}", employeeId);
        Optional<Employee> optionalEmployee = employeeRepository.findByIdActive(employeeId);
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
            Employee updated = employeeRepository.save(employee);
            return mapper.toEmployeeDTO(updated);
        }
        return null;
    }
    
    @Override
    public boolean deleteEmployee(Integer employeeId) {
        log.info("Deleting employee with ID: {}", employeeId);
        Optional<Employee> optionalEmployee = employeeRepository.findByIdActive(employeeId);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setIsActive(false);
            employeeRepository.save(employee);
            return true;
        }
        return false;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<EmployeeDTO> getEmployeeById(Integer employeeId) {
        log.info("Fetching employee with ID: {}", employeeId);
        return employeeRepository.findByIdActive(employeeId)
                .map(mapper::toEmployeeDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> getAllEmployees() {
        log.info("Fetching all employees");
        return employeeRepository.findAllActive()
                .stream()
                .map(mapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<EmployeeDTO> getEmployeeByEmail(String email) {
        log.info("Fetching employee by email: {}", email);
        return employeeRepository.findByEmail(email)
                .map(mapper::toEmployeeDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> getEmployeesByFirstNameIgnoreCase(String firstName) {
        log.info("Fetching employees by first name: {}", firstName);
        return employeeRepository.findByFirstNameIgnoreCase(firstName)
                .stream()
                .map(mapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> getEmployeesByLastNameIgnoreCase(String lastName) {
        log.info("Fetching employees by last name: {}", lastName);
        return employeeRepository.findByLastNameIgnoreCase(lastName)
                .stream()
                .map(mapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> getEmployeesByCityIgnoreCase(String city) {
        log.info("Fetching employees by city: {}", city);
        return employeeRepository.findByCityIgnoreCase(city)
                .stream()
                .map(mapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> getEmployeesByTitleIgnoreCase(String title) {
        log.info("Fetching employees by title: {}", title);
        return employeeRepository.findByTitleIgnoreCase(title)
                .stream()
                .map(mapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> getEmployeesByCountryIgnoreCase(String country) {
        log.info("Fetching employees by country: {}", country);
        return employeeRepository.findByCountryIgnoreCase(country)
                .stream()
                .map(mapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<EmployeeDTO> getEmployeeByEmailIgnoreCase(String email) {
        log.info("Fetching employee by email (case-insensitive): {}", email);
        return employeeRepository.findByEmail(email)
                .map(mapper::toEmployeeDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> searchEmployees(String firstName, String lastName, String city, String title, String country) {
        log.info("Searching employees with filters - firstName: {}, lastName: {}, city: {}, title: {}, country: {}", 
                 firstName, lastName, city, title, country);
        
        List<EmployeeDTO> results = getAllEmployees();
        
        if (firstName != null && !firstName.isEmpty()) {
            results = results.stream()
                    .filter(e -> e.getFirstName() != null && 
                               e.getFirstName().toLowerCase().contains(firstName.toLowerCase()))
                    .collect(Collectors.toList());
        }
        
        if (lastName != null && !lastName.isEmpty()) {
            results = results.stream()
                    .filter(e -> e.getLastName() != null && 
                               e.getLastName().toLowerCase().contains(lastName.toLowerCase()))
                    .collect(Collectors.toList());
        }
        
        if (city != null && !city.isEmpty()) {
            results = results.stream()
                    .filter(e -> e.getCity() != null && 
                               e.getCity().toLowerCase().contains(city.toLowerCase()))
                    .collect(Collectors.toList());
        }
        
        if (title != null && !title.isEmpty()) {
            results = results.stream()
                    .filter(e -> e.getTitle() != null && 
                               e.getTitle().toLowerCase().contains(title.toLowerCase()))
                    .collect(Collectors.toList());
        }
        
        if (country != null && !country.isEmpty()) {
            results = results.stream()
                    .filter(e -> e.getCountry() != null && 
                               e.getCountry().toLowerCase().contains(country.toLowerCase()))
                    .collect(Collectors.toList());
        }
        
        return results;
    }
}
