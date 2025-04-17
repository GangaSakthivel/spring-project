package com.example.cage.repository;

import com.example.cage.model.Cage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CageRepository extends JpaRepository<Cage, Long> {

    @Query("SELECT SUM(c.quantity) FROM Cage c WHERE c.shop.name = :shopName")
    Long getTotalQuantityByShopName(@Param("shopName") String shopName);

}

