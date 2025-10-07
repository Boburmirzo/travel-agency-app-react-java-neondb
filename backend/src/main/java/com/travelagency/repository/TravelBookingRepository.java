package com.travelagency.repository;

import com.travelagency.model.TravelBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TravelBookingRepository extends JpaRepository<TravelBooking, Long> {
    List<TravelBooking> findByUserId(Long userId);
    List<TravelBooking> findByDestinationId(Long destinationId);
    Optional<TravelBooking> findByUuid(String uuid);
}