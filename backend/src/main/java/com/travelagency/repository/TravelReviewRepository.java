package com.travelagency.repository;

import com.travelagency.model.TravelReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TravelReviewRepository extends JpaRepository<TravelReview, Long> {
    List<TravelReview> findByUserId(Long userId);
    List<TravelReview> findByDestinationId(Long destinationId);
    Optional<TravelReview> findByUuid(String uuid);
}