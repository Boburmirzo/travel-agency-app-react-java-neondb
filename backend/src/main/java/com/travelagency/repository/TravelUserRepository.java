package com.travelagency.repository;

import com.travelagency.model.TravelUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TravelUserRepository extends JpaRepository<TravelUser, Long> {
    Optional<TravelUser> findByEmail(String email);
    Optional<TravelUser> findByUuid(String uuid);
    boolean existsByEmail(String email);
}