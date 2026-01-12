package com.bikeshop.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record AccessoryDto(
        UUID id,
        String name,
        String description,
        BigDecimal price) {
}
