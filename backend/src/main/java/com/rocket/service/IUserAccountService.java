package com.rocket.service;

import com.rocket.dto.UserAccountDTO;
import java.util.List;
import java.util.Optional;

public interface IUserAccountService {
    
    UserAccountDTO createUserAccount(UserAccountDTO userAccountDTO);
    
    UserAccountDTO updateUserAccount(Integer userId, UserAccountDTO userAccountDTO);
    
    void deactivateUserAccount(Integer userId);
    
    void deleteUserAccount(Integer userId);
    
    UserAccountDTO getUserAccountById(Integer userId);
    
    UserAccountDTO getUserAccountByUsername(String username);
    
    UserAccountDTO getUserAccountByEmployeeId(Integer employeeId);
    
    List<UserAccountDTO> getAllUserAccounts();
    
    boolean isAccountActive(Integer userId);
    
    boolean userExists(Integer userId);
}
