package com.nikitalipatov.springcity.controllers;

import com.nikitalipatov.springcity.dtos.CarDto;
import com.nikitalipatov.springcity.dtos.CarRecord;
import com.nikitalipatov.springcity.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/car")
public class CarController {

    private final CarService carService;

    @PostMapping(value = "/create/{userId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public CarDto create(@PathVariable int userId, @RequestBody CarRecord carRecord) {
        return carService.create(userId, carRecord);
    }

    @GetMapping(value = "/list")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<CarDto> getAll() {
        return carService.getAll();
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void delete(@PathVariable int id) {
        carService.deleteCar(id);
    }

    @PutMapping(value = "/edit/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public CarDto edit(@PathVariable int id, @RequestBody CarRecord carRecord) {
        return carService.edit(id, carRecord);
    }
}
