package com.bikeshop.mapper;

import com.bikeshop.dto.BikeCreateDto;
import com.bikeshop.dto.BikeDto;
import com.bikeshop.entity.Bike;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BikeMapper {

    public BikeDto toDto(Bike bike) {
        if (bike == null) {
            return null;
        }
        return new BikeDto(
                bike.getId(),
                bike.getName(),
                bike.getCategory(),
                bike.getDescription(),
                bike.getPrice());
    }

    public List<BikeDto> toDtoList(List<Bike> bikes) {
        return bikes.stream()
                .map(this::toDto)
                .toList();
    }

    public Bike toEntity(BikeCreateDto dto) {
        if (dto == null) {
            return null;
        }
        return Bike.builder()
                .name(dto.name())
                .category(dto.category())
                .description(dto.description())
                .price(dto.price())
                .build();
    }
}
