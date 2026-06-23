package com.rocket.repository;

import com.rocket.entity.LeadSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LeadSourceRepository extends JpaRepository<LeadSource, Integer> {
    
    Optional<LeadSource> findBySourceName(String sourceName);
}
