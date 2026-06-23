package com.rocket.repository;

import com.rocket.entity.LeadStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LeadStatusRepository extends JpaRepository<LeadStatus, Integer> {
    
    Optional<LeadStatus> findByStatusName(String statusName);
}
