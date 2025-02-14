package com.inditexproof.domain.price.model.entity;

import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@NoArgsConstructor
public class Price {
    private PriceId id;
    private PriceBrandId brandId;
    private PriceProductId productId;
    private PricePriceList priceList;
    private PriceStartDate startDate;
    private PriceEndDate endDate;
    private PricePriority priority;
    private PricePrice price;
    private PriceCurrency currency;

    public Price(int id,
                 int brandId,
                 int productId,
                 int priceList,
                 LocalDateTime startDate,
                 LocalDateTime endDate,
                 int priority,
                 BigDecimal price,
                 String currency) {
        this.id = new PriceId(id);
        this.brandId = new PriceBrandId(brandId);
        this.productId = new PriceProductId(productId);
        this.priceList = new PricePriceList(priceList);
        this.startDate = new PriceStartDate(startDate);
        this.endDate = new PriceEndDate(endDate);
        this.priority = new PricePriority(priority);
        this.price = new PricePrice(price);
        this.currency = new PriceCurrency(currency);
    }

    public int getId() {
        return this.id.getId();
    }

    public int getBrandId() {
        return this.brandId.getBrandId();
    }

    public int getProductId() {
        return this.productId.getProductId();
    }

    public int getPriceList() {
        return this.priceList.getPriceList();
    }

    public LocalDateTime getStartDate() {
        return this.startDate.getStartDate();
    }

    public LocalDateTime getEndDate() {
        return this.endDate.getEndDate();
    }

    public int getPriority() {
        return this.priority.getPriority();
    }

    public BigDecimal getPrice() {
        return this.price.getPrice();
    }

    public String getCurrency() {
        return this.currency.getCurrency();
    }

}
