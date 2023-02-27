package com.nikitalipatov.springcity.repository;

import com.nikitalipatov.springcity.model.CarShop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarShopRepository extends JpaRepository<CarShop, Integer> {
}
