package com.travelagency.repository;

import com.travelagency.model.TravelDestination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TravelDestinationRepository extends JpaRepository<TravelDestination, Long> {
    Optional<TravelDestination> findByName(String name);
    Optional<TravelDestination> findByUuid(String uuid);
    boolean existsByName(String name);
}