package com.inditexproof.domain.price.model.exception;

public class PriceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errorMessage;

    public PriceNotFoundException(String errorMessage) {

        this.errorMessage = errorMessage;
    }

    public PriceNotFoundException() {
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
