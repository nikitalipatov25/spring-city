package com.nikitalipatov.springcity.repos;

import com.nikitalipatov.springcity.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
    void deleteAllByIdIn(List<Integer> ids);
}
