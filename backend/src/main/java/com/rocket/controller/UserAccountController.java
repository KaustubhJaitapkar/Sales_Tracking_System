package com.rocket.controller;

import com.rocket.entity.UserAccount;
import com.rocket.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-accounts")
public class UserAccountController {
    
    @Autowired
    private UserAccountService userAccountService;
    
    @PostMapping
    public ResponseEntity<UserAccount> createUserAccount(@RequestBody UserAccount userAccount) {
        try {
            UserAccount createdAccount = userAccountService.createUserAccount(userAccount);
            return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserAccount> updateUserAccount(@PathVariable Integer id, @RequestBody UserAccount userAccountDetails) {
        UserAccount updatedAccount = userAccountService.updateUserAccount(id, userAccountDetails);
        if (updatedAccount != null) {
            return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateUserAccount(@PathVariable Integer id) {
        boolean deactivated = userAccountService.deactivateUserAccount(id);
        if (deactivated) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserAccount(@PathVariable Integer id) {
        boolean deleted = userAccountService.deleteUserAccount(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserAccount> getUserAccountById(@PathVariable Integer id) {
        Optional<UserAccount> userAccount = userAccountService.getUserAccountById(id);
        return userAccount.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                          .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @GetMapping("/username/{username}")
    public ResponseEntity<UserAccount> getUserAccountByUsername(@PathVariable String username) {
        try {
            Optional<UserAccount> userAccount = userAccountService.getUserAccountByUsername(username);
            return userAccount.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                             .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<UserAccount> getUserAccountByEmployeeId(@PathVariable Integer employeeId) {
        try {
            Optional<UserAccount> userAccount = userAccountService.getUserAccountByEmployeeId(employeeId);
            return userAccount.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                             .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping
    public ResponseEntity<List<UserAccount>> getAllUserAccountsUnpaged() {
        List<UserAccount> userAccounts = userAccountService.getAllUserAccounts();
        return new ResponseEntity<>(userAccounts, HttpStatus.OK);
    }
    
    @GetMapping("/{id}/is-active")
    public ResponseEntity<Boolean> isAccountActive(@PathVariable Integer id) {
        boolean isActive = userAccountService.isAccountActive(id);
        return new ResponseEntity<>(isActive, HttpStatus.OK);
    }
}
