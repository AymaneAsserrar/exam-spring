package com.bikeshop.mapper;

import com.bikeshop.dto.CartDto;
import com.bikeshop.entity.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class CartMapper {

    private final BikeMapper bikeMapper;
    private final AccessoryMapper accessoryMapper;

    public CartDto toDto(Cart cart) {
        if (cart == null) {
            return null;
        }

        var bikeDtos = cart.getBikes().stream()
                .map(bikeMapper::toDto)
                .toList();

        var accessoryDtos = cart.getAccessories().stream()
                .map(accessoryMapper::toDto)
                .toList();

        var bikesTotal = cart.getBikes().stream()
                .map(bike -> bike.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var accessoriesTotal = cart.getAccessories().stream()
                .map(accessory -> accessory.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var totalPrice = bikesTotal.add(accessoriesTotal);

        return new CartDto(
                cart.getId(),
                cart.getUserId(),
                bikeDtos,
                accessoryDtos,
                totalPrice);
    }
}
