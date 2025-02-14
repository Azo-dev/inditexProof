package com.inditexproof.infrastructure.price.adapter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="prices")
public class PriceEntity {

    @Id
    private int id;
    private int brandId;
    private int productId;
    private int priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int priority;
    private BigDecimal price;
    private String currency;
}
