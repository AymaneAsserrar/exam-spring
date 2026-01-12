package com.bikeshop.service;

import com.bikeshop.dto.AccessoryCreateDto;
import com.bikeshop.dto.AccessoryDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface AccessoryService {

    List<AccessoryDto> getAllAccessories();

    AccessoryDto getAccessoryById(UUID id);

    List<AccessoryDto> filterByPrice(BigDecimal minPrice, BigDecimal maxPrice);

    AccessoryDto createAccessory(AccessoryCreateDto createDto);

    void deleteAccessory(UUID id);
}
