package com.nikitalipatov.springcity.services;

import com.nikitalipatov.springcity.contracts.CarService;
import com.nikitalipatov.springcity.dtos.CarRecord;
import com.nikitalipatov.springcity.dtos.PersonRecord;
import com.nikitalipatov.springcity.models.Car;
import com.nikitalipatov.springcity.models.Person;
import com.nikitalipatov.springcity.repos.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> getByGosNumber(String gosNumber) {
        return carRepository.findById(gosNumber);
    }

    @Override
    public Car create(CarRecord carRecord) {
        Car car = new Car(carRecord.gosNumber(), carRecord.model(),
                carRecord.name(), carRecord.type(), carRecord.color()
        );
        return carRepository.save(car);
    }

}
