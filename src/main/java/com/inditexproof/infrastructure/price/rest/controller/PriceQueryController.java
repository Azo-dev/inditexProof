package com.inditexproof.infrastructure.price.rest.controller;

import com.inditexproof.application.price.query.FindApplicablePriceHandler;
import com.inditexproof.domain.price.model.dto.ApplicablePriceDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/prices")
public class PriceQueryController {
    private final FindApplicablePriceHandler findApplicablePriceHandler;

    public PriceQueryController(FindApplicablePriceHandler findApplicablePriceHandler) {
        this.findApplicablePriceHandler = findApplicablePriceHandler;
    }

    @GetMapping
    public ApplicablePriceDto getPriceByDateAndProductIdAndBrandId (
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
            @RequestParam int productId,
            @RequestParam int brandId) {

        return findApplicablePriceHandler.execute(applicationDate, productId, brandId);
    }
}
