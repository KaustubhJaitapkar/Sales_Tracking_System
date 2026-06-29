package com.rocket.controller;

import com.rocket.dto.UserAccountDTO;
import com.rocket.dto.ApiResponse;
import com.rocket.service.IUserAccountService;
import com.rocket.exception.ResourceNotFoundException;
import com.rocket.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/user-accounts")
@RequiredArgsConstructor
@Validated
@Slf4j
public class UserAccountController {
    
    private final IUserAccountService userAccountService;
    
    /**
     * Create a new user account
     * @param userAccountDTO the user account data transfer object
     * @return ResponseEntity with created account and HTTP 201
     */
    @PostMapping
    public ResponseEntity<ApiResponse<UserAccountDTO>> createUserAccount(
            @Valid @RequestBody UserAccountDTO userAccountDTO) {
        log.info("Creating user account for username: {}", userAccountDTO.getUsername());
        
        UserAccountDTO createdAccount = userAccountService.createUserAccount(userAccountDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "User account created successfully", createdAccount));
    }
    
    /**
     * Update an existing user account
     * @param id the user account ID
     * @param userAccountDTO the updated user account data
     * @return ResponseEntity with updated account and HTTP 200
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserAccountDTO>> updateUserAccount(
            @PathVariable @NotNull(message = "User ID cannot be null") Integer id,
            @Valid @RequestBody UserAccountDTO userAccountDTO) {
        log.info("Updating user account with ID: {}", id);
        
        UserAccountDTO updatedAccount = userAccountService.updateUserAccount(id, userAccountDTO);
        
        return ResponseEntity.ok(new ApiResponse<>(true, "User account updated successfully", updatedAccount));
    }
    
    /**
     * Deactivate a user account
     * @param id the user account ID
     * @return ResponseEntity with success message and HTTP 200
     */
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<ApiResponse<Void>> deactivateUserAccount(
            @PathVariable @NotNull(message = "User ID cannot be null") Integer id) {
        log.info("Deactivating user account with ID: {}", id);
        
        userAccountService.deactivateUserAccount(id);
        
        return ResponseEntity.ok(new ApiResponse<>(true, "User account deactivated successfully"));
    }
    
    /**
     * Delete a user account
     * @param id the user account ID
     * @return ResponseEntity with success message and HTTP 204
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUserAccount(
            @PathVariable @NotNull(message = "User ID cannot be null") Integer id) {
        log.info("Deleting user account with ID: {}", id);
        
        userAccountService.deleteUserAccount(id);
        
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ApiResponse<>(true, "User account deleted successfully"));
    }
    
    /**
     * Get user account by ID
     * @param id the user account ID
     * @return ResponseEntity with user account data and HTTP 200
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserAccountDTO>> getUserAccountById(
            @PathVariable @NotNull(message = "User ID cannot be null") Integer id) {
        log.info("Fetching user account with ID: {}", id);
        
        UserAccountDTO userAccount = userAccountService.getUserAccountById(id);
        
        return ResponseEntity.ok(new ApiResponse<>(true, "User account retrieved successfully", userAccount));
    }
    
    /**
     * Get user account by username
     * @param username the username
     * @return ResponseEntity with user account data and HTTP 200
     */
    @GetMapping("/username/{username}")
    public ResponseEntity<ApiResponse<UserAccountDTO>> getUserAccountByUsername(
            @PathVariable @NotBlank(message = "Username cannot be blank") String username) {
        log.info("Fetching user account with username: {}", username);
        
        UserAccountDTO userAccount = userAccountService.getUserAccountByUsername(username);
        
        return ResponseEntity.ok(new ApiResponse<>(true, "User account retrieved successfully", userAccount));
    }
    
    /**
     * Get user account by employee ID
     * @param employeeId the employee ID
     * @return ResponseEntity with user account data and HTTP 200
     */
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<ApiResponse<UserAccountDTO>> getUserAccountByEmployeeId(
            @PathVariable @NotNull(message = "Employee ID cannot be null") Integer employeeId) {
        log.info("Fetching user account for employee ID: {}", employeeId);
        
        UserAccountDTO userAccount = userAccountService.getUserAccountByEmployeeId(employeeId);
        
        return ResponseEntity.ok(new ApiResponse<>(true, "User account retrieved successfully", userAccount));
    }
    
    /**
     * Get all user accounts
     * @return ResponseEntity with list of all user accounts and HTTP 200
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserAccountDTO>>> getAllUserAccounts() {
        log.info("Fetching all user accounts");
        
        List<UserAccountDTO> userAccounts = userAccountService.getAllUserAccounts();
        
        return ResponseEntity.ok(new ApiResponse<>(true, "User accounts retrieved successfully", userAccounts));
    }
    
    /**
     * Check if account is active
     * @param id the user account ID
     * @return ResponseEntity with boolean status and HTTP 200
     */
    @GetMapping("/{id}/is-active")
    public ResponseEntity<ApiResponse<Boolean>> isAccountActive(
            @PathVariable @NotNull(message = "User ID cannot be null") Integer id) {
        log.info("Checking if user account is active. ID: {}", id);
        
        boolean isActive = userAccountService.isAccountActive(id);
        
        return ResponseEntity.ok(new ApiResponse<>(true, "Account status retrieved successfully", isActive));
    }
}
