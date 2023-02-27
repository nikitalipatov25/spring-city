package com.nikitalipatov.springcity.service.impl;

import com.nikitalipatov.springcity.converter.CarConverter;
import com.nikitalipatov.springcity.dto.CarDto;
import com.nikitalipatov.springcity.dto.CarRecord;
import com.nikitalipatov.springcity.dto.CarShopRecord;
import com.nikitalipatov.springcity.exeption.ResourceNotFoundException;
import com.nikitalipatov.springcity.model.Car;
import com.nikitalipatov.springcity.model.CarShop;
import com.nikitalipatov.springcity.repository.CarRepository;
import com.nikitalipatov.springcity.repository.PersonRepository;
import com.nikitalipatov.springcity.service.CarService;
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
    private final CarConverter converter;
    private final PersonRepository personRepository;

    @Override
    public List<CarDto> getAll() {
        return converter.toDto(carRepository.findAll());
    }

    @Override
    @Transactional
    public CarDto create(int personId, CarRecord carRecord) {
        Car car = new Car(carRecord.gosNumber(), carRecord.model(), carRecord.type(), carRecord.color(), carRecord.name());
        car.setPerson(personRepository.findById(personId).get());
        System.out.println(car);
        return converter.toDto(carRepository.save(car));
    }

    @Override
    @Transactional
    public void deleteCar(int carId) {
        carRepository.delete(getCar(carId));
    }

    @Override
    @Transactional
    public CarDto edit(int carId, CarRecord carRecord) {
        Car car = getCar(carId);
        return converter.toDto(carRepository.save(converter.toEntity(car, carRecord)));
    }

    @Override
    public Car getCar(int carId) {
        return carRepository.findById(carId).orElseThrow(
                () -> new ResourceNotFoundException("No such car with id " + carId)
        );
    }

    @Override
    @Transactional
    public Set<Car> createCarSet (List<CarRecord> carRecordList, CarShop carShop) {
        Set<Car> carSet = new HashSet<>();
        for (int i = 0; i < carRecordList.size(); i++) {
            carSet.add(carRepository.save(converter.toEntity(new Car(), carRecordList.get(i), carShop)));
        }
        return carSet;
    }
}
