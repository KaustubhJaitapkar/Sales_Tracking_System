package com.rocket.repository;

import com.rocket.entity.Locations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface LocationsRepository extends JpaRepository<Locations, Integer> {
    
    @Query("SELECT l FROM Locations l WHERE l.city = ?1 AND l.isActive = true")
    List<Locations> findByCity(String city);
    
    @Query("SELECT l FROM Locations l WHERE l.country = ?1 AND l.isActive = true")
    List<Locations> findByCountry(String country);
    
    @Query("SELECT l FROM Locations l WHERE l.isActive = true")
    List<Locations> findAllActive();
    
    @Query("SELECT l FROM Locations l WHERE l.id = ?1 AND l.isActive = true")
    Optional<Locations> findByIdActive(Integer id);
}
