package com.travelagency.controller;

import com.travelagency.model.TravelBooking;
import com.travelagency.repository.TravelBookingRepository;
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
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:3000")
public class TravelBookingController {
    
    @Autowired
    private TravelBookingRepository bookingRepository;
    
    @Autowired
    private TravelUserRepository userRepository;
    
    @Autowired
    private TravelDestinationRepository destinationRepository;
    
    @GetMapping
    public List<TravelBooking> getAllBookings() {
        return bookingRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TravelBooking> getBookingById(@PathVariable Long id) {
        Optional<TravelBooking> booking = bookingRepository.findById(id);
        return booking.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/user/{userId}")
    public List<TravelBooking> getBookingsByUserId(@PathVariable Long userId) {
        return bookingRepository.findByUserId(userId);
    }
    
    @PostMapping
    public ResponseEntity<TravelBooking> createBooking(@Valid @RequestBody TravelBooking booking) {
        // Validate that user and destination exist
        if (!userRepository.existsById(booking.getUserId())) {
            return ResponseEntity.badRequest().build();
        }
        
        if (!destinationRepository.existsById(booking.getDestinationId())) {
            return ResponseEntity.badRequest().build();
        }
        
        // Generate UUID if not provided
        if (booking.getUuid() == null || booking.getUuid().isEmpty()) {
            booking.setUuid(UUID.randomUUID().toString());
        }
        
        TravelBooking savedBooking = bookingRepository.save(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBooking);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TravelBooking> updateBooking(@PathVariable Long id, @Valid @RequestBody TravelBooking bookingDetails) {
        Optional<TravelBooking> optionalBooking = bookingRepository.findById(id);
        
        if (optionalBooking.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        TravelBooking booking = optionalBooking.get();
        
        // Validate that user and destination exist if they are being changed
        if (bookingDetails.getUserId() != null && !bookingDetails.getUserId().equals(booking.getUserId())) {
            if (!userRepository.existsById(bookingDetails.getUserId())) {
                return ResponseEntity.badRequest().build();
            }
            booking.setUserId(bookingDetails.getUserId());
        }
        
        if (bookingDetails.getDestinationId() != null && !bookingDetails.getDestinationId().equals(booking.getDestinationId())) {
            if (!destinationRepository.existsById(bookingDetails.getDestinationId())) {
                return ResponseEntity.badRequest().build();
            }
            booking.setDestinationId(bookingDetails.getDestinationId());
        }
        
        if (bookingDetails.getDate() != null) {
            booking.setDate(bookingDetails.getDate());
        }
        if (bookingDetails.getStatus() != null) {
            booking.setStatus(bookingDetails.getStatus());
        }
        if (bookingDetails.getNumberOfPeople() != null) {
            booking.setNumberOfPeople(bookingDetails.getNumberOfPeople());
        }
        
        TravelBooking updatedBooking = bookingRepository.save(booking);
        return ResponseEntity.ok(updatedBooking);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        if (!bookingRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        bookingRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}