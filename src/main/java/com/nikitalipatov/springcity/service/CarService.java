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

    void deleteCar(int carId);

    CarDto edit(int carId, CarRecord carRecord);

    Car getCar(int carId);

    Set<Car> createCarSet (List<CarRecord> carRecordList, CarShop carShop);
}
