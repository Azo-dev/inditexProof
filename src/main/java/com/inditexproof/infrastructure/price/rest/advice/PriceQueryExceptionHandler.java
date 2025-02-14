package com.inditexproof.infrastructure.price.rest.advice;

import com.inditexproof.domain.price.model.exception.PriceBadRequestException;
import com.inditexproof.domain.price.model.exception.PriceNotFoundException;
import com.inditexproof.infrastructure.price.rest.exception.InvalidPriceQueryParameterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class PriceQueryExceptionHandler {

    @ExceptionHandler(PriceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String, Object>> handlePriceNotFoundException(PriceNotFoundException ex) {
        return buildErrorResponse("Price not found", ex.getErrorMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        String paramName = ex.getName();
        String expectedType = (ex.getRequiredType() != null) ? ex.getRequiredType().getSimpleName() : "Unknown";

        String detailMessage = String.format(
                "Invalid parameter: '%s'. Expected type: %s. Ensure the value format is correct.",
                paramName, expectedType
        );

        return buildErrorResponse("Invalid request parameter", detailMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPriceQueryParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleInvalidPriceQueryParameter(InvalidPriceQueryParameterException ex) {
        return buildErrorResponse("Price query error", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PriceBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleBadRequest(PriceBadRequestException ex) {
        String errorMessage = ex.getErrorMessage() != null ? ex.getErrorMessage() : "Unknown error details";
        return buildErrorResponse("Bad Request",
                errorMessage,
                HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(String error, String message, HttpStatus status) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("error", error);
        errorResponse.put("message", message);
        errorResponse.put("status", status.value());

        return new ResponseEntity<>(errorResponse, status);
    }
}
