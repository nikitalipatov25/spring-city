package com.nikitalipatov.springcity.repos;

import com.nikitalipatov.springcity.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, String> {
}
