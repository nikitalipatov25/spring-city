package com.nikitalipatov.springcity.services;

import com.nikitalipatov.springcity.dtos.CarRecord;
import com.nikitalipatov.springcity.models.Car;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CarService {

    List<Car> getAll();

    Optional<Car> getByGosNumber(String gosNumber);

    Car create(CarRecord carRecord);

    void delete(List<Integer> carIds);

    void deleteCar(int id);

    Car edit(int id, CarRecord carRecord);

    Car getCar(int carId);
}
