package com.bikeshop.repository;

import com.bikeshop.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface BikeRepository extends JpaRepository<Bike, UUID> {

    List<Bike> findByCategory(String category);

    List<Bike> findByPriceLessThanEqual(BigDecimal maxPrice);

    @Query("SELECT b FROM Bike b WHERE " +
            "(:category IS NULL OR b.category = :category) AND " +
            "(:maxPrice IS NULL OR b.price <= :maxPrice)")
    List<Bike> findByFilters(
            @Param("category") String category,
            @Param("maxPrice") BigDecimal maxPrice);
}
