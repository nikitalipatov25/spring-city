package com.nikitalipatov.springcity.contracts;

import com.nikitalipatov.springcity.dtos.CarRecord;
import com.nikitalipatov.springcity.models.Car;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CarService {

    List<Car> getAll();

    Optional<Car> getByGosNumber(String gosNumber);

    Car create(CarRecord carRecord);

    void delete(Set<Car> cars);

    HttpStatus deleteCar(int id);

    Car edit(int id, CarRecord carRecord);
}
