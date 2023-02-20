package com.nikitalipatov.springcity.repos;

import com.nikitalipatov.springcity.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {
    Optional<Car> findByGosNumber(String gosNumber);

    void deleteAllByIdIn(List<Integer> ids);
}
