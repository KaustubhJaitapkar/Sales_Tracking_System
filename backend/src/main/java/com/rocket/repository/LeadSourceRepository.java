package com.rocket.repository;

import com.rocket.entity.LeadSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface LeadSourceRepository extends JpaRepository<LeadSource, Integer> {
    
    @Query("SELECT ls FROM LeadSource ls WHERE ls.sourceName = ?1 AND ls.isActive = true")
    Optional<LeadSource> findBySourceName(String sourceName);
    
    @Query("SELECT ls FROM LeadSource ls WHERE ls.isActive = true")
    List<LeadSource> findAllActive();
    
    @Query("SELECT ls FROM LeadSource ls WHERE ls.sourceId = ?1 AND ls.isActive = true")
    Optional<LeadSource> findByIdActive(Integer sourceId);
}
