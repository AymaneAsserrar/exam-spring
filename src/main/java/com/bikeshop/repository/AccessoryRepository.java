package com.bikeshop.repository;

import com.bikeshop.entity.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface AccessoryRepository extends JpaRepository<Accessory, UUID> {

    List<Accessory> findByPriceLessThanEqual(BigDecimal maxPrice);

    @Query("SELECT a FROM Accessory a WHERE " +
            "(:minPrice IS NULL OR a.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR a.price <= :maxPrice)")
    List<Accessory> findByPriceRange(
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice);
}
