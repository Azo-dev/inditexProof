package com.inditexproof.infrastructure.price.adapter.jpa;

import com.inditexproof.infrastructure.price.adapter.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceSpringJpaAdapterRepository extends JpaRepository<PriceEntity, Long> {

    @Query("SELECT p FROM PriceEntity p " +
            "WHERE p.brandId = :brandId " +
            "AND p.productId = :productId " +
            "AND :applicationDate BETWEEN p.startDate AND p.endDate " +
            "ORDER BY p.priority DESC LIMIT 1")
    Optional<PriceEntity> getByDateAndProductIdAndBrandId(
            @Param("applicationDate") LocalDateTime applicationDate,
            @Param("productId") int productId,
            @Param("brandId") int brandId
    );

}
