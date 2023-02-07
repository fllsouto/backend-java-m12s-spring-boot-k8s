package com.cdc.backend.shoppingApi.repository;

import java.util.List;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdc.backend.shoppingApi.model.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

    public List<Shop> findAllByUserIdentifier(String userIdentifier);
    public List<Shop> findAllByTotalGreaterThan(Float total);
    public List<Shop> findAllByDateGreaterThan(Date date);
}
