package com.bikeshop.mapper;

import com.bikeshop.dto.AccessoryCreateDto;
import com.bikeshop.dto.AccessoryDto;
import com.bikeshop.entity.Accessory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccessoryMapper {

    public AccessoryDto toDto(Accessory accessory) {
        if (accessory == null) {
            return null;
        }
        return new AccessoryDto(
                accessory.getId(),
                accessory.getName(),
                accessory.getDescription(),
                accessory.getPrice());
    }

    public List<AccessoryDto> toDtoList(List<Accessory> accessories) {
        return accessories.stream()
                .map(this::toDto)
                .toList();
    }

    public Accessory toEntity(AccessoryCreateDto dto) {
        if (dto == null) {
            return null;
        }
        return Accessory.builder()
                .name(dto.name())
                .description(dto.description())
                .price(dto.price())
                .build();
    }
}
