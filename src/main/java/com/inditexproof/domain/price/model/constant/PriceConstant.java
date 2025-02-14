package com.inditexproof.domain.price.model.constant;

public class PriceConstant {
    public static final String PRICE_NOT_FOUND_MESSAGE_ERROR = "ERROR::PRICE NOT FOUND";

    public static final String ERROR_FIELD = "$.error";
    public static final String MESSAGE_FIELD = "$.message";

    public static final String INVALID_PARAMETER_ERROR = "Invalid request parameter";
    public static final String INVALID_DATE_MESSAGE = "Invalid parameter: 'applicationDate'. Expected type: LocalDateTime. Ensure the value format is correct.";

    public static final String NOT_FOUND_ERROR = "Price not found";
    public static final String NOT_FOUND_MESSAGE = "Price not found for given parameters";

    public static final String BAD_REQUEST_ERROR = "Bad Request";
    public static final String BAD_REQUEST_MESSAGE = "Invalid request parameters. Expected: applicationDate (ISO 8601 DateTime), productId (Integer), brandId (Integer). Missing required parameter";

    public static final String PRICE_QUERY_ERROR = "Price query error";
    public static final String PRICE_QUERY_MESSAGE = "Invalid price query parameters";
}
