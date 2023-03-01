package com.nikitalipatov.springcity.service;

import com.nikitalipatov.springcity.dto.CarDto;
import com.nikitalipatov.springcity.dto.CarRecord;
import com.nikitalipatov.springcity.model.Car;
import com.nikitalipatov.springcity.model.CarShop;

import java.util.List;
import java.util.Set;


public interface CarService {

    List<CarDto> getAll();

    CarDto create(int personId, CarRecord carRecord);

    List<Integer> create(List<CarRecord> carRecord);

    void deleteCar(int carId);

    CarDto editCar(int carId, CarRecord carRecord);

    Car getCar(int carId);
}
