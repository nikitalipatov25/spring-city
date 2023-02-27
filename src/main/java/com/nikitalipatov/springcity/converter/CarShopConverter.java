package com.nikitalipatov.springcity.converter;

import com.nikitalipatov.springcity.dto.CarDto;
import com.nikitalipatov.springcity.dto.CarRecord;
import com.nikitalipatov.springcity.dto.CarShopDto;
import com.nikitalipatov.springcity.dto.CarShopRecord;
import com.nikitalipatov.springcity.model.Car;
import com.nikitalipatov.springcity.model.CarShop;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CarShopConverter {

    private final CarConverter carConverter;

    public CarShop toEntity(CarShop carShop, CarShopRecord carShopRecord) {
        return carShop.toBuilder()
                .name(carShopRecord.name())
                .cars(carShop.getCars())
                .build();
    }

    public CarShop toEntity(CarShop carShop, Set<Car> carSet) {
        carShop.getCars().addAll(carSet);
        System.out.println(carShop.getCars().size());
        return carShop.toBuilder()
                .cars(carShop.getCars())
                .build();
    }

    public CarShopDto toDto(CarShop carShop) {
        return CarShopDto.builder()
                .name(carShop.getName())
                .cars(carShop.getCars() == null ? null : carConverter.toDto(carShop.getCars().stream().toList()))
                .build();
    }
//
//    public List<CarDto> toDto(List<Car> carList) {
//        var cars = new ArrayList<CarDto>();
//        for (Car car : carList) {
//            cars.add(toDto(car));
//        }
//        return cars;
//    }
}
