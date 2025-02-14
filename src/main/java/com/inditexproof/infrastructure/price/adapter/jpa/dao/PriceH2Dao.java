package com.inditexproof.infrastructure.price.adapter.jpa.dao;

import com.inditexproof.domain.price.model.entity.Price;
import com.inditexproof.domain.price.port.dao.PriceDao;
import com.inditexproof.infrastructure.price.service.PriceService;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class PriceH2Dao implements PriceDao {

    private final PriceService priceService;

    public PriceH2Dao(PriceService priceService) {
        this.priceService = priceService;
    }

    @Override
    public Optional<Price> getByDateAndProductIdAndBrandId(LocalDateTime applicationDate, int productId, int brandId) {
        return Optional.ofNullable(priceService.findPrice(applicationDate, productId, brandId));
    }
}
