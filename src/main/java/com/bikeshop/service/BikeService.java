package com.bikeshop.service;

import com.bikeshop.dto.BikeCreateDto;
import com.bikeshop.dto.BikeDto;
import com.bikeshop.dto.BikeSearchCriteria;

import java.util.List;
import java.util.UUID;

public interface BikeService {

    List<BikeDto> getAllBikes();

    BikeDto getBikeById(UUID id);

    List<BikeDto> searchBikes(BikeSearchCriteria criteria);

    BikeDto createBike(BikeCreateDto createDto);

    void deleteBike(UUID id);
}
