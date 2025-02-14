package com.inditexproof.application.price.mapper;

import com.inditexproof.domain.price.model.dto.ApplicablePriceDto;
import com.inditexproof.domain.price.model.entity.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ApplicablePriceDtoMapper {

    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "brandId", target = "brandId")
    @Mapping(source = "priceList", target = "priceList")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    @Mapping(source = "price", target = "price")
    ApplicablePriceDto toDto(Price domain);
}
