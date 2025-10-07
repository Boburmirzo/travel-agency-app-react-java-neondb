package com.travelagency.controller;

import com.travelagency.model.TravelReview;
import com.travelagency.repository.TravelReviewRepository;
import com.travelagency.repository.TravelUserRepository;
import com.travelagency.repository.TravelDestinationRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "http://localhost:3000")
public class TravelReviewController {
    
    @Autowired
    private TravelReviewRepository reviewRepository;
    
    @Autowired
    private TravelUserRepository userRepository;
    
    @Autowired
    private TravelDestinationRepository destinationRepository;
    
    @GetMapping
    public List<TravelReview> getAllReviews() {
        return reviewRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TravelReview> getReviewById(@PathVariable Long id) {
        Optional<TravelReview> review = reviewRepository.findById(id);
        return review.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/destination/{destinationId}")
    public List<TravelReview> getReviewsByDestinationId(@PathVariable Long destinationId) {
        return reviewRepository.findByDestinationId(destinationId);
    }
    
    @PostMapping
    public ResponseEntity<TravelReview> createReview(@Valid @RequestBody TravelReview review) {
        // Validate that user and destination exist
        if (!userRepository.existsById(review.getUserId())) {
            return ResponseEntity.badRequest().build();
        }
        
        if (!destinationRepository.existsById(review.getDestinationId())) {
            return ResponseEntity.badRequest().build();
        }
        
        // Generate UUID if not provided
        if (review.getUuid() == null || review.getUuid().isEmpty()) {
            review.setUuid(UUID.randomUUID().toString());
        }
        
        TravelReview savedReview = reviewRepository.save(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TravelReview> updateReview(@PathVariable Long id, @Valid @RequestBody TravelReview reviewDetails) {
        Optional<TravelReview> optionalReview = reviewRepository.findById(id);
        
        if (optionalReview.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        TravelReview review = optionalReview.get();
        
        // Validate that user and destination exist if they are being changed
        if (reviewDetails.getUserId() != null && !reviewDetails.getUserId().equals(review.getUserId())) {
            if (!userRepository.existsById(reviewDetails.getUserId())) {
                return ResponseEntity.badRequest().build();
            }
            review.setUserId(reviewDetails.getUserId());
        }
        
        if (reviewDetails.getDestinationId() != null && !reviewDetails.getDestinationId().equals(review.getDestinationId())) {
            if (!destinationRepository.existsById(reviewDetails.getDestinationId())) {
                return ResponseEntity.badRequest().build();
            }
            review.setDestinationId(reviewDetails.getDestinationId());
        }
        
        if (reviewDetails.getRating() != null) {
            review.setRating(reviewDetails.getRating());
        }
        if (reviewDetails.getComment() != null) {
            review.setComment(reviewDetails.getComment());
        }
        
        TravelReview updatedReview = reviewRepository.save(review);
        return ResponseEntity.ok(updatedReview);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        if (!reviewRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        reviewRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}