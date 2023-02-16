package com.nikitalipatov.springcity.contracts;

import com.nikitalipatov.springcity.dtos.CarRecord;
import com.nikitalipatov.springcity.models.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> getAll();

    Optional<Car> getByGosNumber(String gosNumber);

    Car create(CarRecord carRecord);
}
