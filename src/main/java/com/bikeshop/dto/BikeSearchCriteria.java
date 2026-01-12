package com.bikeshop.dto;

import java.math.BigDecimal;

public record BikeSearchCriteria(
        String category,
        BigDecimal maxPrice) {
}
