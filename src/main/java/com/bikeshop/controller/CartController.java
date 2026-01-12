package com.bikeshop.controller;

import com.bikeshop.dto.CartDto;
import com.bikeshop.service.CartService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
@Validated
@Slf4j
public class CartController {

    private final CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getCart(
            @PathVariable @NotBlank(message = "User ID is required") String userId) {

        log.info("GET /api/carts/{} - Fetching cart for user", userId);
        var cart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/{userId}/bikes/{bikeId}")
    public ResponseEntity<CartDto> addBikeToCart(
            @PathVariable @NotBlank(message = "User ID is required") String userId,
            @PathVariable UUID bikeId) {

        log.info("POST /api/carts/{}/bikes/{} - Adding bike to cart", userId, bikeId);
        var cart = cartService.addBikeToCart(userId, bikeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(cart);
    }

    @DeleteMapping("/{userId}/bikes/{bikeId}")
    public ResponseEntity<CartDto> removeBikeFromCart(
            @PathVariable @NotBlank(message = "User ID is required") String userId,
            @PathVariable UUID bikeId) {

        log.info("DELETE /api/carts/{}/bikes/{} - Removing bike from cart", userId, bikeId);
        var cart = cartService.removeBikeFromCart(userId, bikeId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/{userId}/accessories/{accessoryId}")
    public ResponseEntity<CartDto> addAccessoryToCart(
            @PathVariable @NotBlank(message = "User ID is required") String userId,
            @PathVariable UUID accessoryId) {

        log.info("POST /api/carts/{}/accessories/{} - Adding accessory to cart", userId, accessoryId);
        var cart = cartService.addAccessoryToCart(userId, accessoryId);
        return ResponseEntity.status(HttpStatus.CREATED).body(cart);
    }

    @DeleteMapping("/{userId}/accessories/{accessoryId}")
    public ResponseEntity<CartDto> removeAccessoryFromCart(
            @PathVariable @NotBlank(message = "User ID is required") String userId,
            @PathVariable UUID accessoryId) {

        log.info("DELETE /api/carts/{}/accessories/{} - Removing accessory from cart", userId, accessoryId);
        var cart = cartService.removeAccessoryFromCart(userId, accessoryId);
        return ResponseEntity.ok(cart);
    }
}
