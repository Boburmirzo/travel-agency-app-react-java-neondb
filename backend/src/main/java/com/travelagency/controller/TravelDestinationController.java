package com.travelagency.controller;

import com.travelagency.model.TravelDestination;
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
@RequestMapping("/api/destinations")
@CrossOrigin(origins = "http://localhost:3000")
public class TravelDestinationController {
    
    @Autowired
    private TravelDestinationRepository destinationRepository;
    
    @GetMapping
    public List<TravelDestination> getAllDestinations() {
        return destinationRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TravelDestination> getDestinationById(@PathVariable Long id) {
        Optional<TravelDestination> destination = destinationRepository.findById(id);
        return destination.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<TravelDestination> createDestination(@Valid @RequestBody TravelDestination destination) {
        // Check if name already exists
        if (destinationRepository.existsByName(destination.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        
        // Generate UUID if not provided
        if (destination.getUuid() == null || destination.getUuid().isEmpty()) {
            destination.setUuid(UUID.randomUUID().toString());
        }
        
        TravelDestination savedDestination = destinationRepository.save(destination);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDestination);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TravelDestination> updateDestination(@PathVariable Long id, @Valid @RequestBody TravelDestination destinationDetails) {
        Optional<TravelDestination> optionalDestination = destinationRepository.findById(id);
        
        if (optionalDestination.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        TravelDestination destination = optionalDestination.get();
        
        // Check if name is being changed and already exists
        if (!destination.getName().equals(destinationDetails.getName()) && 
            destinationRepository.existsByName(destinationDetails.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        
        destination.setName(destinationDetails.getName());
        destination.setDescription(destinationDetails.getDescription());
        destination.setPrice(destinationDetails.getPrice());
        if (destinationDetails.getRating() != null) {
            destination.setRating(destinationDetails.getRating());
        }
        
        TravelDestination updatedDestination = destinationRepository.save(destination);
        return ResponseEntity.ok(updatedDestination);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDestination(@PathVariable Long id) {
        if (!destinationRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        destinationRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}