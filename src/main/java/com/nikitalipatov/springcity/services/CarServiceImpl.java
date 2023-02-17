package com.nikitalipatov.springcity.services;

import com.nikitalipatov.springcity.contracts.CarService;
import com.nikitalipatov.springcity.dtos.CarRecord;
import com.nikitalipatov.springcity.exeptions.ResourceNotFoundException;
import com.nikitalipatov.springcity.models.Car;
import com.nikitalipatov.springcity.repos.CarRepository;
import com.nikitalipatov.springcity.repos.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final PersonRepository personRepository;

    @Override
    public List<Car> getAll() {
        var result = carRepository.findAll();
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("No content");
        } else {
            return result;
        }
    }

    @Override
    public Optional<Car> getByGosNumber(String gosNumber) {
        return carRepository.findByGosNumber(gosNumber);
    }

    @Override
    public Car create(CarRecord carRecord) {
        Car car = new Car(carRecord.gosNumber(), carRecord.model(),
                carRecord.name(), carRecord.type(), carRecord.color()
        );
        return carRepository.save(car);
    }

    @Override
    public void delete(Set<Car> cars) {
        Optional<Car> car;
        var carList = cars.stream().toList();
        for (int i = 0; i < cars.size(); i++) {
            car = carRepository.findById(carList.get(i).getId());
            carRepository.delete(car.get());
        }
    }

    @Override
    public HttpStatus deleteCar(int id) {
        var car = carRepository.findById(id);
        return car
                .map(e -> {
                    var persons = personRepository.findAll();
                    for (int i = 0; i < persons.size(); i++) {
                        persons.get(i).getCar().removeIf(c -> c.getId() == id);
                    }
                    personRepository.saveAll(persons);
                    carRepository.delete(car.get());
                    return HttpStatus.NO_CONTENT;
                })
                .orElseThrow(() -> new ResourceNotFoundException("No such house with id " + id));
    }

    @Override
    public Car edit(int id, CarRecord carRecord) {
        var car = carRepository.findById(id);
        return car
                .map(e -> {
                    e.setGosNumber(carRecord.gosNumber());
                    e.setColor(carRecord.color());
                    e.setModel(carRecord.model());
                    e.setName(carRecord.name());
                    e.setType(carRecord.type());
                    return carRepository.save(e);
                })
                .orElseThrow(() -> new ResourceNotFoundException("No such house with id " + id));
    }
}
