package com.inditexproof.infrastructure.price.rest.controller;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @CsvSource({
            "2020-06-14T10:00:00, 35455, 1, 35.50",
            "2020-06-14T16:00:00, 35455, 1, 25.45",
            "2020-06-14T21:00:00, 35455, 1, 35.50",
            "2020-06-15T10:00:00, 35455, 1, 30.50",
            "2020-06-16T21:00:00, 35455, 1, 38.95"
    })
    void testPriceQuery(String applicationDate, int productId, int brandId, double expectedPrice) throws Exception {
        MvcResult result = mockMvc.perform(get("/prices")
                        .param("applicationDate", applicationDate)
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(expectedPrice))
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        assertNotNull(responseContent, "La respuesta del endpoint no debería ser nula");
        assertEquals(200, result.getResponse().getStatus(), "El código de estado debería ser 200 OK");

    }
}
