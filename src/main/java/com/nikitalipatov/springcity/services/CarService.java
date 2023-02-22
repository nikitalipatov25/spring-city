package com.nikitalipatov.springcity.services;

import com.nikitalipatov.springcity.dtos.CarDto;
import com.nikitalipatov.springcity.dtos.CarRecord;
import com.nikitalipatov.springcity.models.Car;
import com.nikitalipatov.springcity.models.Person;

import java.util.List;


public interface CarService {

    List<CarDto> getAll();

    CarDto create(int personId, CarRecord carRecord);

    void deleteCar(int carId);

    CarDto edit(int carId, CarRecord carRecord);

    Car getCar(int carId);
}
