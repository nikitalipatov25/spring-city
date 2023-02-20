package com.nikitalipatov.springcity.services.impl;

import com.nikitalipatov.springcity.converters.CarConverter;
import com.nikitalipatov.springcity.dtos.CarRecord;
import com.nikitalipatov.springcity.exeptions.ResourceNotFoundException;
import com.nikitalipatov.springcity.models.Car;
import com.nikitalipatov.springcity.repos.CarRepository;
import com.nikitalipatov.springcity.repos.PersonRepository;
import com.nikitalipatov.springcity.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarConverter converter;

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> getByGosNumber(String gosNumber) {
        return carRepository.findByGosNumber(gosNumber);
    }

    @Override
    public Car create(CarRecord carRecord) {
        return carRepository.save(new Car(carRecord.gosNumber(), carRecord.model(),
                carRecord.name(), carRecord.type(), carRecord.color()
        ));
    }

    @Override
    public void delete(List<Integer> carIds) {
        carRepository.deleteAllByIdIn(carIds);
    }

    @Override
    @Transactional
    public void deleteCar(int carId) {
        carRepository.delete(getCar(carId));
    }

    @Override
    public Car edit(int carId, CarRecord carRecord) {
        return carRepository.save(converter.edit(getCar(carId), carRecord));
    }

    @Override
    public Car getCar(int id) {
        return carRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No such car with id " + id)
        );
    }
}
