package com.inditexproof.domain.price.port.dao;

import com.inditexproof.domain.price.model.entity.Price;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceDao {
    Optional<Price> getByDateAndProductIdAndBrandId(LocalDateTime applicationDate, int productId, int brandId);
}
