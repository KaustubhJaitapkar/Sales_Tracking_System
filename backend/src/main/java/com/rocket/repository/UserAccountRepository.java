package com.rocket.repository;

import com.rocket.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
    
    Optional<UserAccount> findByUsername(String username);
    
    Optional<UserAccount> findByEmployeeId(Integer employeeId);
}
