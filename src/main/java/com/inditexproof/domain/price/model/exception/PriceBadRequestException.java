package com.inditexproof.domain.price.model.exception;

public class PriceBadRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errorMessage;

    public PriceBadRequestException(String errorMessage) {

        this.errorMessage = errorMessage;
    }

    public PriceBadRequestException() {
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
