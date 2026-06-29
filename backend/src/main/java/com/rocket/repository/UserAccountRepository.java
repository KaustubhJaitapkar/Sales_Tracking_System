package com.rocket.repository;

import com.rocket.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
    
    @Query("SELECT u FROM UserAccount u WHERE u.username = ?1 AND u.isActive = true")
    Optional<UserAccount> findByUsername(String username);
    
    @Query("SELECT u FROM UserAccount u WHERE u.employee.id = ?1 AND u.isActive = true")
    Optional<UserAccount> findByEmployeeId(Integer employeeId);
    
    @Query("SELECT u FROM UserAccount u WHERE u.isActive = true")
    List<UserAccount> findAllActive();
    
    @Query("SELECT u FROM UserAccount u WHERE u.userId = ?1 AND u.isActive = true")
    Optional<UserAccount> findByIdActive(Integer userId);
}
