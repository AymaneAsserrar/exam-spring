package com.bikeshop.service.impl;

import com.bikeshop.dto.BikeCreateDto;
import com.bikeshop.dto.BikeDto;
import com.bikeshop.dto.BikeSearchCriteria;
import com.bikeshop.entity.Bike;
import com.bikeshop.exception.ResourceNotFoundException;
import com.bikeshop.mapper.BikeMapper;
import com.bikeshop.repository.BikeRepository;
import com.bikeshop.service.BikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class BikeServiceImpl implements BikeService {

    private final BikeRepository bikeRepository;
    private final BikeMapper bikeMapper;

    @Override
    public List<BikeDto> getAllBikes() {
        log.debug("Fetching all bikes");
        var bikes = bikeRepository.findAll();
        return bikeMapper.toDtoList(bikes);
    }

    @Override
    public BikeDto getBikeById(UUID id) {
        log.debug("Fetching bike with id: {}", id);
        var bike = findBikeOrThrow(id);
        return bikeMapper.toDto(bike);
    }

    @Override
    public List<BikeDto> searchBikes(BikeSearchCriteria criteria) {
        log.debug("Searching bikes with criteria: category={}, maxPrice={}",
                criteria.category(), criteria.maxPrice());

        var bikes = bikeRepository.findByFilters(
                criteria.category(),
                criteria.maxPrice());

        return bikeMapper.toDtoList(bikes);
    }

    @Override
    @Transactional
    public BikeDto createBike(BikeCreateDto createDto) {
        log.debug("Creating new bike: {}", createDto.name());
        var bike = bikeMapper.toEntity(createDto);
        var savedBike = bikeRepository.save(bike);
        return bikeMapper.toDto(savedBike);
    }

    @Override
    @Transactional
    public void deleteBike(UUID id) {
        log.debug("Deleting bike with id: {}", id);
        var bike = findBikeOrThrow(id);
        bikeRepository.delete(bike);
    }

    private Bike findBikeOrThrow(UUID id) {
        return bikeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bike", "id", id));
    }
}
