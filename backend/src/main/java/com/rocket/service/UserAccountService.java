package com.rocket.service;

import com.rocket.entity.UserAccount;
import com.rocket.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
public class UserAccountService {
    
    @Autowired
    private UserAccountRepository userAccountRepository;
    
    // Create a new user account
    public UserAccount createUserAccount(UserAccount userAccount) {
        if (userAccount == null) {
            throw new IllegalArgumentException("UserAccount cannot be null");
        }
        if (userAccount.getUsername() == null || userAccount.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (userAccount.getPasswordHash() == null || userAccount.getPasswordHash().trim().isEmpty()) {
            throw new IllegalArgumentException("Password hash cannot be null or empty");
        }
        
        // Check if username already exists
        Optional<UserAccount> existingAccount = userAccountRepository.findByUsername(userAccount.getUsername());
        if (existingAccount.isPresent()) {
            throw new IllegalArgumentException("Username already exists: " + userAccount.getUsername());
        }
        
        // Set default values
        if (userAccount.getAccountStatus() == null || userAccount.getAccountStatus().trim().isEmpty()) {
            userAccount.setAccountStatus("ACTIVE");
        }
        if (userAccount.getCreatedAt() == null) {
            userAccount.setCreatedAt(LocalDateTime.now());
        }
        
        return userAccountRepository.save(userAccount);
    }
    
    // Update a user account
    public UserAccount updateUserAccount(Integer userId, UserAccount userAccountDetails) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        Optional<UserAccount> optionalUserAccount = userAccountRepository.findById(userId);
        if (optionalUserAccount.isPresent()) {
            UserAccount userAccount = optionalUserAccount.get();
            if (userAccountDetails.getUsername() != null && !userAccountDetails.getUsername().trim().isEmpty()) {
                // Check if new username is unique (excluding current user)
                Optional<UserAccount> existingAccount = userAccountRepository.findByUsername(userAccountDetails.getUsername());
                if (existingAccount.isPresent() && !existingAccount.get().getUserId().equals(userId)) {
                    throw new IllegalArgumentException("Username already exists: " + userAccountDetails.getUsername());
                }
                userAccount.setUsername(userAccountDetails.getUsername());
            }
            if (userAccountDetails.getPasswordHash() != null && !userAccountDetails.getPasswordHash().trim().isEmpty()) {
                userAccount.setPasswordHash(userAccountDetails.getPasswordHash());
            }
            if (userAccountDetails.getAccountStatus() != null && !userAccountDetails.getAccountStatus().trim().isEmpty()) {
                userAccount.setAccountStatus(userAccountDetails.getAccountStatus());
            }
            if (userAccountDetails.getEmployee() != null) {
                userAccount.setEmployee(userAccountDetails.getEmployee());
            }
            return userAccountRepository.save(userAccount);
        }
        return null;
    }
    
    // Deactivate a user account
    public boolean deactivateUserAccount(Integer userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        Optional<UserAccount> optionalUserAccount = userAccountRepository.findById(userId);
        if (optionalUserAccount.isPresent()) {
            UserAccount userAccount = optionalUserAccount.get();
            userAccount.setAccountStatus("INACTIVE");
            userAccountRepository.save(userAccount);
            return true;
        }
        return false;
    }
    
    // Delete a user account
    public boolean deleteUserAccount(Integer userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        if (userAccountRepository.existsById(userId)) {
            userAccountRepository.deleteById(userId);
            return true;
        }
        return false;
    }
    
    // Get user account by ID
    public Optional<UserAccount> getUserAccountById(Integer userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        return userAccountRepository.findById(userId);
    }
    
    // Get all user accounts
    public List<UserAccount> getAllUserAccounts() {
        return userAccountRepository.findAll();
    }
    
    // Get user account by username
    public Optional<UserAccount> getUserAccountByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        return userAccountRepository.findByUsername(username);
    }
    
    // Get user account by employee ID
    public Optional<UserAccount> getUserAccountByEmployeeId(Integer employeeId) {
        if (employeeId == null) {
            throw new IllegalArgumentException("Employee ID cannot be null");
        }
        return userAccountRepository.findByEmployeeId(employeeId);
    }
    
    // Check if account is active
    public boolean isAccountActive(Integer userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        Optional<UserAccount> userAccount = userAccountRepository.findById(userId);
        return userAccount.filter(account -> "ACTIVE".equals(account.getAccountStatus())).isPresent();
    }
}
