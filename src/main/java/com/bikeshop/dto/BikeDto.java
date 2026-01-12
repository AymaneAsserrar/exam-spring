package com.bikeshop.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record BikeDto(
        UUID id,
        String name,
        String category,
        String description,
        BigDecimal price) {
}
