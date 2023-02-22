package com.nikitalipatov.springcity.repository;

import com.nikitalipatov.springcity.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
    void deleteAllByIdIn(List<Integer> ids);
}
