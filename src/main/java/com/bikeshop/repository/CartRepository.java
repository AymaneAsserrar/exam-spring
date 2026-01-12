package com.bikeshop.repository;

import com.bikeshop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {

    @Query("SELECT c FROM Cart c LEFT JOIN FETCH c.bikes LEFT JOIN FETCH c.accessories WHERE c.userId = :userId")
    Optional<Cart> findByUserIdWithBikes(@Param("userId") String userId);

    Optional<Cart> findByUserId(String userId);

    boolean existsByUserId(String userId);
}
