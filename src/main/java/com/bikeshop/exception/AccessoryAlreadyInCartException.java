package com.bikeshop.exception;

public class AccessoryAlreadyInCartException extends RuntimeException {

    public AccessoryAlreadyInCartException(String message) {
        super(message);
    }
}
