package com.bikeshop.exception;

public class BikeAlreadyInCartException extends RuntimeException {

    public BikeAlreadyInCartException(String message) {
        super(message);
    }
}
