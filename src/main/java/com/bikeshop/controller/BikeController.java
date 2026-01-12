package com.bikeshop.controller;

import com.bikeshop.dto.BikeCreateDto;
import com.bikeshop.dto.BikeDto;
import com.bikeshop.dto.BikeSearchCriteria;
import com.bikeshop.service.BikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/bikes")
@RequiredArgsConstructor
@Slf4j
public class BikeController {

    private final BikeService bikeService;

    @GetMapping
    public ResponseEntity<List<BikeDto>> getAllBikes() {
        log.info("GET /api/bikes - Fetching all bikes");
        var bikes = bikeService.getAllBikes();
        return ResponseEntity.ok(bikes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BikeDto> getBikeById(@PathVariable UUID id) {
        log.info("GET /api/bikes/{} - Fetching bike by id", id);
        var bike = bikeService.getBikeById(id);
        return ResponseEntity.ok(bike);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BikeDto>> searchBikes(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) BigDecimal maxPrice) {

        log.info("GET /api/bikes/search - Searching bikes with category={}, maxPrice={}",
                category, maxPrice);

        var criteria = new BikeSearchCriteria(category, maxPrice);
        var bikes = bikeService.searchBikes(criteria);
        return ResponseEntity.ok(bikes);
    }

    @PostMapping
    public ResponseEntity<BikeDto> createBike(@Valid @RequestBody BikeCreateDto createDto) {
        log.info("POST /api/bikes - Creating new bike: {}", createDto.name());
        var bike = bikeService.createBike(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(bike);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBike(@PathVariable UUID id) {
        log.info("DELETE /api/bikes/{} - Deleting bike", id);
        bikeService.deleteBike(id);
        return ResponseEntity.noContent().build();
    }
}
