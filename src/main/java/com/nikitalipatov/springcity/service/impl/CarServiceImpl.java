package com.nikitalipatov.springcity.service.impl;

import com.nikitalipatov.springcity.converter.CarConverter;
import com.nikitalipatov.springcity.dto.CarDto;
import com.nikitalipatov.springcity.dto.CarRecord;
import com.nikitalipatov.springcity.exeption.ResourceNotFoundException;
import com.nikitalipatov.springcity.model.Car;
import com.nikitalipatov.springcity.model.CarShop;
import com.nikitalipatov.springcity.repository.CarRepository;
import com.nikitalipatov.springcity.repository.PersonRepository;
import com.nikitalipatov.springcity.service.CarService;
import com.nikitalipatov.springcity.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarConverter carConverter;
    private final PersonService personService;

    @Override
    public List<CarDto> getAll() {
        return carConverter.toDto(carRepository.findAll());
    }

    @Override
    @Transactional
    public CarDto create(int personId, CarRecord carRecord) {
        Car car = new Car(carRecord.gosNumber(), carRecord.model(), carRecord.type(), carRecord.color(), carRecord.name());
        car.setPerson(personService.getPerson(personId));
        System.out.println(car);
        return carConverter.toDto(carRepository.save(car));
    }

    @Override
    @Transactional
    public List<Integer> create(List<CarRecord> carRecords) {
        var cars = carConverter.toEntity(carRecords);
        carRepository.saveAll(cars);
        return cars.stream()
                .map((Car::getId))
                .toList();
    }

    @Override
    @Transactional
    public void deleteCar(int carId) {
        carRepository.delete(getCar(carId));
    }

    @Override
    @Transactional
    public CarDto editCar(int carId, CarRecord carRecord) {
        Car car = getCar(carId);
        return carConverter.toDto(carRepository.save(carConverter.toEntity(car, carRecord)));
    }

    @Override
    public Car getCar(int carId) {
        return carRepository.findById(carId).orElseThrow(
                () -> new ResourceNotFoundException("No such car with id " + carId)
        );
    }
}
