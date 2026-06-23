package com.rocket.repository;

import com.rocket.entity.Locations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LocationsRepository extends JpaRepository<Locations, Integer> {
    
    Optional<Locations> findByCity(String city);
    
    Optional<Locations> findByCountry(String country);
}
