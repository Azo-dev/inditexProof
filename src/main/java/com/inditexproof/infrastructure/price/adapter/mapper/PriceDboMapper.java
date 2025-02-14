package com.inditexproof.infrastructure.price.adapter.mapper;

import com.inditexproof.domain.price.model.entity.Price;
import com.inditexproof.infrastructure.price.adapter.entity.PriceEntity;
import org.springframework.stereotype.Component;

@Component
public class PriceDboMapper {

    public Price toDomain(PriceEntity priceEntity){
        if(priceEntity == null){
            return null;
        }

        return new Price(priceEntity.getId(),
                priceEntity.getBrandId(),
                priceEntity.getProductId(),
                priceEntity.getPriceList(),
                priceEntity.getStartDate(),
                priceEntity.getEndDate(),
                priceEntity.getPriority(),
                priceEntity.getPrice(),
                priceEntity.getCurrency());

    }

    public PriceEntity toDbo(Price domain){
        if(domain == null){
            return null;
        }

        return new PriceEntity(domain.getId(),
                domain.getBrandId(),
                domain.getProductId(),
                domain.getPriceList(),
                domain.getStartDate(),
                domain.getEndDate(),
                domain.getPriority(),
                domain.getPrice(),
                domain.getCurrency());

    }

}
