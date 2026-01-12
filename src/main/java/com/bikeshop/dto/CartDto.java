package com.bikeshop.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CartDto(
        UUID id,
        String userId,
        List<BikeDto> bikes,
        List<AccessoryDto> accessories,
        BigDecimal totalPrice) {
}
