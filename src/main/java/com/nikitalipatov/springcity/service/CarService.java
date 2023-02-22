package com.nikitalipatov.springcity.service;

import com.nikitalipatov.springcity.dto.CarDto;
import com.nikitalipatov.springcity.dto.CarRecord;
import com.nikitalipatov.springcity.model.Car;

import java.util.List;


public interface CarService {

    List<CarDto> getAll();

    CarDto create(int personId, CarRecord carRecord);

    void deleteCar(int carId);

    CarDto edit(int carId, CarRecord carRecord);

    Car getCar(int carId);
}
