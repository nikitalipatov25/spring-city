package com.nikitalipatov.springcity.controllers;

import com.nikitalipatov.springcity.contracts.CarService;
import com.nikitalipatov.springcity.dtos.CarRecord;
import com.nikitalipatov.springcity.dtos.PersonRecord;
import com.nikitalipatov.springcity.models.Car;
import com.nikitalipatov.springcity.models.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/car")
public class CarController {

        private final CarService carService;

        @PostMapping(value = "/create")
        @ResponseStatus(value = HttpStatus.ACCEPTED)
        public Car create(@RequestBody CarRecord carRecord) {
                return carService.create(carRecord);
        }

        @GetMapping(value = "/list")
        @ResponseStatus(value = HttpStatus.ACCEPTED)
        public List<Car> getAll() {
                return carService.getAll();
        }
}
