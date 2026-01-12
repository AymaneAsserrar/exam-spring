package com.bikeshop.controller;

import com.bikeshop.dto.AccessoryCreateDto;
import com.bikeshop.dto.AccessoryDto;
import com.bikeshop.service.AccessoryService;
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
@RequestMapping("/api/accessories")
@RequiredArgsConstructor
@Slf4j
public class AccessoryController {

    private final AccessoryService accessoryService;

    @GetMapping
    public ResponseEntity<List<AccessoryDto>> getAllAccessories() {
        log.info("GET /api/accessories - Fetching all accessories");
        var accessories = accessoryService.getAllAccessories();
        return ResponseEntity.ok(accessories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccessoryDto> getAccessoryById(@PathVariable UUID id) {
        log.info("GET /api/accessories/{} - Fetching accessory by id", id);
        var accessory = accessoryService.getAccessoryById(id);
        return ResponseEntity.ok(accessory);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<AccessoryDto>> filterByPrice(
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice) {

        log.info("GET /api/accessories/filter - Filtering accessories with minPrice={}, maxPrice={}",
                minPrice, maxPrice);

        var accessories = accessoryService.filterByPrice(minPrice, maxPrice);
        return ResponseEntity.ok(accessories);
    }

    @PostMapping
    public ResponseEntity<AccessoryDto> createAccessory(@Valid @RequestBody AccessoryCreateDto createDto) {
        log.info("POST /api/accessories - Creating new accessory: {}", createDto.name());
        var accessory = accessoryService.createAccessory(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(accessory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccessory(@PathVariable UUID id) {
        log.info("DELETE /api/accessories/{} - Deleting accessory", id);
        accessoryService.deleteAccessory(id);
        return ResponseEntity.noContent().build();
    }
}
