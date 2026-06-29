package com.rocket.service.impl;

import com.rocket.dto.UserAccountDTO;
import com.rocket.entity.UserAccount;
import com.rocket.entity.Employee;
import com.rocket.exception.ResourceNotFoundException;
import com.rocket.exception.ValidationException;
import com.rocket.mapper.EntityDTOMapper;
import com.rocket.repository.UserAccountRepository;
import com.rocket.repository.EmployeeRepository;
import com.rocket.service.IUserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
// import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserAccountServiceImpl implements IUserAccountService {
    
    private final UserAccountRepository userAccountRepository;
    private final EmployeeRepository employeeRepository;
    private final EntityDTOMapper mapper;
    
    @Override
    public UserAccountDTO createUserAccount(UserAccountDTO userAccountDTO) {
        validateUserAccountDTO(userAccountDTO);
        
        // Check if username already exists
        if (userAccountRepository.findByUsername(userAccountDTO.getUsername()).isPresent()) {
            throw new ValidationException("Username '" + userAccountDTO.getUsername() + "' already exists");
        }
        
        // Fetch employee
        Employee employee = employeeRepository.findById(userAccountDTO.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee with ID " + userAccountDTO.getEmployeeId() + " not found"));
        
        // Check if employee already has an account
        if (employee.getUserAccount() != null) {
            throw new ValidationException("Employee with ID " + userAccountDTO.getEmployeeId() + " already has a user account");
        }
        
        UserAccount userAccount = UserAccount.builder()
                .username(userAccountDTO.getUsername())
                .passwordHash(userAccountDTO.getPassword()) // In production, use PasswordEncoder
                .accountStatus(userAccountDTO.getAccountStatus() != null ? userAccountDTO.getAccountStatus() : "ACTIVE")
                .employee(employee)
                .createdAt(LocalDateTime.now())
                .build();
        
        UserAccount savedAccount = userAccountRepository.save(userAccount);
        return mapper.toUserAccountDTO(savedAccount);
    }
    
    @Override
    public UserAccountDTO updateUserAccount(Integer userId, UserAccountDTO userAccountDTO) {
        UserAccount userAccount = userAccountRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User account with ID " + userId + " not found"));
        
        // Update username if provided and different
        if (userAccountDTO.getUsername() != null && !userAccountDTO.getUsername().equals(userAccount.getUsername())) {
            if (userAccountRepository.findByUsername(userAccountDTO.getUsername()).isPresent()) {
                throw new ValidationException("Username '" + userAccountDTO.getUsername() + "' already exists");
            }
            userAccount.setUsername(userAccountDTO.getUsername());
        }
        
        // Update password if provided
        if (userAccountDTO.getPassword() != null && !userAccountDTO.getPassword().isEmpty()) {
            userAccount.setPasswordHash(userAccountDTO.getPassword());
        }
        
        // Update account status if provided
        if (userAccountDTO.getAccountStatus() != null && !userAccountDTO.getAccountStatus().isEmpty()) {
            userAccount.setAccountStatus(userAccountDTO.getAccountStatus());
        }
        
        UserAccount updatedAccount = userAccountRepository.save(userAccount);
        return mapper.toUserAccountDTO(updatedAccount);
    }
    
    @Override
    public void deactivateUserAccount(Integer userId) {
        UserAccount userAccount = userAccountRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User account with ID " + userId + " not found"));
        
        userAccount.setAccountStatus("INACTIVE");
        userAccountRepository.save(userAccount);
    }
    
    @Override
    public void deleteUserAccount(Integer userId) {
        UserAccount userAccount = userAccountRepository.findByIdActive(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User account with ID " + userId + " not found"));
        userAccount.setIsActive(false);
        userAccountRepository.save(userAccount);
    }
    
    @Override
    @Transactional(readOnly = true)
    public UserAccountDTO getUserAccountById(Integer userId) {
        UserAccount userAccount = userAccountRepository.findByIdActive(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User account with ID " + userId + " not found"));
        return mapper.toUserAccountDTO(userAccount);
    }
    
    @Override
    @Transactional(readOnly = true)
    public UserAccountDTO getUserAccountByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new ValidationException("Username cannot be null or empty");
        }
        UserAccount userAccount = userAccountRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User account with username '" + username + "' not found"));
        return mapper.toUserAccountDTO(userAccount);
    }
    
    @Override
    @Transactional(readOnly = true)
    public UserAccountDTO getUserAccountByEmployeeId(Integer employeeId) {
        if (employeeId == null) {
            throw new ValidationException("Employee ID cannot be null");
        }
        UserAccount userAccount = userAccountRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("User account for employee ID " + employeeId + " not found"));
        return mapper.toUserAccountDTO(userAccount);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<UserAccountDTO> getAllUserAccounts() {
        return userAccountRepository.findAllActive()
                .stream()
                .map(mapper::toUserAccountDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean isAccountActive(Integer userId) {
        if (userId == null) {
            throw new ValidationException("User ID cannot be null");
        }
        return userAccountRepository.findById(userId)
                .map(account -> "ACTIVE".equalsIgnoreCase(account.getAccountStatus()))
                .orElse(false);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean userExists(Integer userId) {
        return userId != null && userAccountRepository.existsById(userId);
    }
    
    private void validateUserAccountDTO(UserAccountDTO dto) {
        if (dto == null) {
            throw new ValidationException("UserAccount data cannot be null");
        }
        if (dto.getUsername() == null || dto.getUsername().trim().isEmpty()) {
            throw new ValidationException("Username is required");
        }
        if (dto.getPassword() == null || dto.getPassword().trim().isEmpty()) {
            throw new ValidationException("Password is required");
        }
        if (dto.getEmployeeId() == null) {
            throw new ValidationException("Employee ID is required");
        }
    }
}
