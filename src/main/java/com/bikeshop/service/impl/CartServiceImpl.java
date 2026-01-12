package com.bikeshop.service.impl;

import com.bikeshop.dto.CartDto;
import com.bikeshop.entity.Accessory;
import com.bikeshop.entity.Bike;
import com.bikeshop.entity.Cart;
import com.bikeshop.exception.AccessoryAlreadyInCartException;
import com.bikeshop.exception.BikeAlreadyInCartException;
import com.bikeshop.exception.ResourceNotFoundException;
import com.bikeshop.mapper.CartMapper;
import com.bikeshop.repository.AccessoryRepository;
import com.bikeshop.repository.BikeRepository;
import com.bikeshop.repository.CartRepository;
import com.bikeshop.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final BikeRepository bikeRepository;
    private final AccessoryRepository accessoryRepository;
    private final CartMapper cartMapper;

    @Override
    public CartDto getCartByUserId(String userId) {
        log.debug("Fetching cart for user: {}", userId);
        var cart = cartRepository.findByUserIdWithBikes(userId)
                .orElseGet(() -> createEmptyCart(userId));
        return cartMapper.toDto(cart);
    }

    @Override
    @Transactional
    public CartDto addBikeToCart(String userId, UUID bikeId) {
        log.debug("Adding bike {} to cart for user: {}", bikeId, userId);

        var bike = findBikeOrThrow(bikeId);
        var cart = findOrCreateCart(userId);

        if (cart.getBikes().contains(bike)) {
            throw new BikeAlreadyInCartException(
                    String.format("Bike '%s' is already in the cart", bike.getName()));
        }

        cart.addBike(bike);
        var savedCart = cartRepository.save(cart);

        return cartMapper.toDto(savedCart);
    }

    @Override
    @Transactional
    public CartDto removeBikeFromCart(String userId, UUID bikeId) {
        log.debug("Removing bike {} from cart for user: {}", bikeId, userId);

        var bike = findBikeOrThrow(bikeId);
        var cart = findCartOrThrow(userId);

        if (!cart.getBikes().contains(bike)) {
            throw new ResourceNotFoundException(
                    String.format("Bike '%s' is not in the cart", bike.getName()));
        }

        cart.removeBike(bike);
        var savedCart = cartRepository.save(cart);

        return cartMapper.toDto(savedCart);
    }

    @Override
    @Transactional
    public CartDto addAccessoryToCart(String userId, UUID accessoryId) {
        log.debug("Adding accessory {} to cart for user: {}", accessoryId, userId);

        var accessory = findAccessoryOrThrow(accessoryId);
        var cart = findOrCreateCart(userId);

        if (cart.getAccessories().contains(accessory)) {
            throw new AccessoryAlreadyInCartException(
                    String.format("Accessory '%s' is already in the cart", accessory.getName()));
        }

        cart.addAccessory(accessory);
        var savedCart = cartRepository.save(cart);

        return cartMapper.toDto(savedCart);
    }

    @Override
    @Transactional
    public CartDto removeAccessoryFromCart(String userId, UUID accessoryId) {
        log.debug("Removing accessory {} from cart for user: {}", accessoryId, userId);

        var accessory = findAccessoryOrThrow(accessoryId);
        var cart = findCartOrThrow(userId);

        if (!cart.getAccessories().contains(accessory)) {
            throw new ResourceNotFoundException(
                    String.format("Accessory '%s' is not in the cart", accessory.getName()));
        }

        cart.removeAccessory(accessory);
        var savedCart = cartRepository.save(cart);

        return cartMapper.toDto(savedCart);
    }

    private Cart findOrCreateCart(String userId) {
        return cartRepository.findByUserIdWithBikes(userId)
                .orElseGet(() -> createEmptyCart(userId));
    }

    private Cart findCartOrThrow(String userId) {
        return cartRepository.findByUserIdWithBikes(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "userId", userId));
    }

    private Cart createEmptyCart(String userId) {
        var cart = Cart.builder()
                .userId(userId)
                .build();
        return cartRepository.save(cart);
    }

    private Bike findBikeOrThrow(UUID bikeId) {
        return bikeRepository.findById(bikeId)
                .orElseThrow(() -> new ResourceNotFoundException("Bike", "id", bikeId));
    }

    private Accessory findAccessoryOrThrow(UUID accessoryId) {
        return accessoryRepository.findById(accessoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Accessory", "id", accessoryId));
    }
}
