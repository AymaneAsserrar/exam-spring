package com.bikeshop.service.impl;

import com.bikeshop.dto.AccessoryCreateDto;
import com.bikeshop.dto.AccessoryDto;
import com.bikeshop.entity.Accessory;
import com.bikeshop.exception.ResourceNotFoundException;
import com.bikeshop.mapper.AccessoryMapper;
import com.bikeshop.repository.AccessoryRepository;
import com.bikeshop.service.AccessoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AccessoryServiceImpl implements AccessoryService {

    private final AccessoryRepository accessoryRepository;
    private final AccessoryMapper accessoryMapper;

    @Override
    public List<AccessoryDto> getAllAccessories() {
        log.debug("Fetching all accessories");
        var accessories = accessoryRepository.findAll();
        return accessoryMapper.toDtoList(accessories);
    }

    @Override
    public AccessoryDto getAccessoryById(UUID id) {
        log.debug("Fetching accessory with id: {}", id);
        var accessory = findAccessoryOrThrow(id);
        return accessoryMapper.toDto(accessory);
    }

    @Override
    public List<AccessoryDto> filterByPrice(BigDecimal minPrice, BigDecimal maxPrice) {
        log.debug("Filtering accessories by price: minPrice={}, maxPrice={}", minPrice, maxPrice);
        var accessories = accessoryRepository.findByPriceRange(minPrice, maxPrice);
        return accessoryMapper.toDtoList(accessories);
    }

    @Override
    @Transactional
    public AccessoryDto createAccessory(AccessoryCreateDto createDto) {
        log.debug("Creating new accessory: {}", createDto.name());
        var accessory = accessoryMapper.toEntity(createDto);
        var savedAccessory = accessoryRepository.save(accessory);
        return accessoryMapper.toDto(savedAccessory);
    }

    @Override
    @Transactional
    public void deleteAccessory(UUID id) {
        log.debug("Deleting accessory with id: {}", id);
        var accessory = findAccessoryOrThrow(id);
        accessoryRepository.delete(accessory);
    }

    private Accessory findAccessoryOrThrow(UUID id) {
        return accessoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accessory", "id", id));
    }
}
