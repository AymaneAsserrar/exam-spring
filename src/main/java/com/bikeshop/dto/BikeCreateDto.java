package com.bikeshop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record BikeCreateDto(
        @NotBlank(message = "Name is required") String name,

        @NotBlank(message = "Category is required") String category,

        String description,

        @NotNull(message = "Price is required") @Positive(message = "Price must be positive") BigDecimal price) {
}
