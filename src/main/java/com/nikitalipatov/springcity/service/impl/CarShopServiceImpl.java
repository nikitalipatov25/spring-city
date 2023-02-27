package com.nikitalipatov.springcity.service.impl;

import com.nikitalipatov.springcity.converter.CarShopConverter;
import com.nikitalipatov.springcity.dto.CarDto;
import com.nikitalipatov.springcity.dto.CarRecord;
import com.nikitalipatov.springcity.dto.CarShopDto;
import com.nikitalipatov.springcity.dto.CarShopRecord;
import com.nikitalipatov.springcity.exeption.ResourceNotFoundException;
import com.nikitalipatov.springcity.model.Car;
import com.nikitalipatov.springcity.model.CarShop;
import com.nikitalipatov.springcity.model.Person;
import com.nikitalipatov.springcity.repository.CarShopRepository;
import com.nikitalipatov.springcity.service.CarService;
import com.nikitalipatov.springcity.service.CarShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CarShopServiceImpl implements CarShopService {

    private final CarShopRepository carShopRepository;
    private final CarShopConverter converter;
    private final CarService carService;

    @Override
    public String preorderStatus() {
        return "Заявка в работе";
    }

    @Override
    @Transactional
    public CarShopDto create(CarShopRecord carShopRecord) {
        return converter.toDto(carShopRepository.save(converter.toEntity(new CarShop(), carShopRecord)));
    }

    @Override
    public CarShopDto addCar(int carShopId, CarShopRecord carShopRecord) {
        CarShop carShop = getCarShop(carShopId);
        Set<Car> carSet = carService.createCarSet(carShopRecord.cars(), carShop);
        return converter.toDto(carShopRepository.save(converter.toEntity(carShop, carSet)));
    }

    @Override
    public CarShop getCarShop(int carShopId) {
        return carShopRepository.findById(carShopId).orElseThrow(
                () -> new ResourceNotFoundException("No such car shop with id " + carShopId)
        );
    }
}
