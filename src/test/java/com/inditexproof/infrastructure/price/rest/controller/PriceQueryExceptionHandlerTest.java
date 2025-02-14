package com.inditexproof.infrastructure.price.rest.controller;

import com.inditexproof.application.price.query.FindApplicablePriceHandler;
import com.inditexproof.domain.price.model.constant.PriceConstant;
import com.inditexproof.domain.price.model.exception.PriceBadRequestException;
import com.inditexproof.domain.price.model.exception.PriceNotFoundException;
import com.inditexproof.infrastructure.price.rest.advice.PriceQueryExceptionHandler;
import com.inditexproof.infrastructure.price.rest.constants.ApiConstants;
import com.inditexproof.infrastructure.price.rest.exception.InvalidPriceQueryParameterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PriceQueryExceptionHandlerTest {

    private MockMvc mockMvc;

    @Mock
    private FindApplicablePriceHandler findApplicablePriceHandler;

    @InjectMocks
    private PriceQueryController priceQueryController;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(priceQueryController)
                .setControllerAdvice(new PriceQueryExceptionHandler())
                .build();
    }

    @Test
    void shouldReturnBadRequestWhenArgumentTypeMismatch() throws Exception {
        performRequest("invalid-date", "35455", "1")
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(PriceConstant.ERROR_FIELD).value(PriceConstant.INVALID_PARAMETER_ERROR))
                .andExpect(jsonPath(PriceConstant.MESSAGE_FIELD).value(PriceConstant.INVALID_DATE_MESSAGE));
    }

    @Test
    void shouldReturnNotFoundWhenPriceNotFoundExceptionOccurs() throws Exception {
        when(findApplicablePriceHandler.execute(Mockito.any(), Mockito.anyInt(), Mockito.anyInt()))
                .thenThrow(new PriceNotFoundException(PriceConstant.NOT_FOUND_MESSAGE));

        performRequest("2025-06-15T11:30:00", "35455", "1")
                .andExpect(status().isNotFound())
                .andExpect(jsonPath(PriceConstant.ERROR_FIELD).value(PriceConstant.NOT_FOUND_ERROR))
                .andExpect(jsonPath(PriceConstant.MESSAGE_FIELD).value(PriceConstant.NOT_FOUND_MESSAGE));
    }

    @Test
    void shouldReturnBadRequestWhenInvalidPriceQueryParameterExceptionOccurs() throws Exception {
        when(findApplicablePriceHandler.execute(Mockito.any(), Mockito.anyInt(), Mockito.anyInt()))
                .thenThrow(new InvalidPriceQueryParameterException(PriceConstant.PRICE_QUERY_MESSAGE));

        performRequest("2025-06-15T11:30:00", "35455", "1")
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(PriceConstant.ERROR_FIELD).value(PriceConstant.PRICE_QUERY_ERROR))
                .andExpect(jsonPath(PriceConstant.MESSAGE_FIELD).value(PriceConstant.PRICE_QUERY_MESSAGE));
    }

    @Test
    void shouldReturnBadRequestWhenPriceBadRequestExceptionOccurs() throws Exception {
        when(findApplicablePriceHandler.execute(Mockito.any(), Mockito.anyInt(), Mockito.anyInt()))
                .thenThrow(new PriceBadRequestException(PriceConstant.BAD_REQUEST_MESSAGE));

        performRequest("2025-06-15T11:30:00", "35455", "1")
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(PriceConstant.ERROR_FIELD).value(PriceConstant.BAD_REQUEST_ERROR))
                .andExpect(jsonPath(PriceConstant.MESSAGE_FIELD).value(PriceConstant.BAD_REQUEST_MESSAGE));
    }

    private org.springframework.test.web.servlet.ResultActions performRequest(String applicationDate, String productId, String brandId) throws Exception {
        return mockMvc.perform(get(ApiConstants.PRICE_ENDPOINT)
                .param(ApiConstants.PARAM_APPLICATION_DATE, applicationDate)
                .param(ApiConstants.PARAM_PRODUCT_ID, productId)
                .param(ApiConstants.PARAM_BRAND_ID, brandId));
    }
}
