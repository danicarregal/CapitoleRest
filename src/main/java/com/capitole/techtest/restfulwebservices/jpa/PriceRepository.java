package com.capitole.techtest.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Integer> {

    List<Price> findByBrandIdAndProductId(int brandId, int productId);
}
