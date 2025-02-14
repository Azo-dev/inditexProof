package com.inditexproof.application.price.query;

import com.inditexproof.application.price.mapper.ApplicablePriceDtoMapper;
import com.inditexproof.domain.price.model.constant.PriceConstant;
import com.inditexproof.domain.price.model.dto.ApplicablePriceDto;
import com.inditexproof.domain.price.model.entity.Price;
import com.inditexproof.domain.price.model.exception.PriceNotFoundException;
import com.inditexproof.domain.price.port.dao.PriceDao;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class FindApplicablePriceHandler {
    private final PriceDao priceDao;
    private final ApplicablePriceDtoMapper applicablePriceDtoMapper;

    public FindApplicablePriceHandler(PriceDao priceDao, ApplicablePriceDtoMapper applicablePriceDtoMapper) {
        this.priceDao = priceDao;
        this.applicablePriceDtoMapper = applicablePriceDtoMapper;
    }

    public ApplicablePriceDto execute(LocalDateTime applicationDate, int productId, int brandId) {
        Optional<Price> optionalPrice = priceDao.getByDateAndProductIdAndBrandId(applicationDate, productId, brandId);
        if (optionalPrice.isPresent()) {
            return applicablePriceDtoMapper.toDto(optionalPrice.get());
        } else {
            throw new PriceNotFoundException(PriceConstant.PRICE_NOT_FOUND_MESSAGE_ERROR);
        }
    }

}
