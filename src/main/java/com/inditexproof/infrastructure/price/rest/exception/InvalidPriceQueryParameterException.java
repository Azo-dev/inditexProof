package com.inditexproof.infrastructure.price.rest.exception;

public class InvalidPriceQueryParameterException extends RuntimeException {
    public InvalidPriceQueryParameterException(String message) {
        super(message);
    }
}
