package com.rocket.repository;

import com.rocket.entity.LeadStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface LeadStatusRepository extends JpaRepository<LeadStatus, Integer> {
    
    @Query("SELECT ls FROM LeadStatus ls WHERE ls.statusName = ?1 AND ls.isActive = true")
    Optional<LeadStatus> findByStatusName(String statusName);
    
    @Query("SELECT ls FROM LeadStatus ls WHERE ls.isActive = true")
    List<LeadStatus> findAllActive();
    
    @Query("SELECT ls FROM LeadStatus ls WHERE ls.statusId = ?1 AND ls.isActive = true")
    Optional<LeadStatus> findByIdActive(Integer statusId);
}
