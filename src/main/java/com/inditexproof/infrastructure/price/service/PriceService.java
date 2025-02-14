package com.inditexproof.infrastructure.price.service;

import com.inditexproof.domain.price.model.entity.Price;
import com.inditexproof.domain.price.model.exception.PriceNotFoundException;
import com.inditexproof.infrastructure.price.adapter.jpa.PriceSpringJpaAdapterRepository;
import com.inditexproof.infrastructure.price.adapter.mapper.PriceDboMapper;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class PriceService {

    private final PriceSpringJpaAdapterRepository priceRepository;
    private final PriceDboMapper priceDboMapper;

    public PriceService(PriceSpringJpaAdapterRepository priceRepository, PriceDboMapper priceDboMapper) {
        this.priceRepository = priceRepository;
        this.priceDboMapper = priceDboMapper;
    }

    public Price findPrice(LocalDateTime applicationDate, int productId, int brandId) {
        return priceRepository.getByDateAndProductIdAndBrandId(applicationDate, productId, brandId)
                .map(priceDboMapper::toDomain)
                .orElseThrow(() -> new PriceNotFoundException(
                        String.format("No price found for product %d and brand %d on %s",
                                productId, brandId, applicationDate)));
    }
}
