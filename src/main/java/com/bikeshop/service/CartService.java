package com.bikeshop.service;

import com.bikeshop.dto.CartDto;

import java.util.UUID;

public interface CartService {

    CartDto getCartByUserId(String userId);

    CartDto addBikeToCart(String userId, UUID bikeId);

    CartDto removeBikeFromCart(String userId, UUID bikeId);

    CartDto addAccessoryToCart(String userId, UUID accessoryId);

    CartDto removeAccessoryFromCart(String userId, UUID accessoryId);
}
