package com.nikitalipatov.springcity.converter;

import com.nikitalipatov.springcity.dto.CarShopDto;
import com.nikitalipatov.springcity.dto.CarShopResponseDto;
import com.nikitalipatov.springcity.model.Car;
import com.nikitalipatov.springcity.model.CarShop;
import com.nikitalipatov.springcity.model.Preorder;
import com.nikitalipatov.springcity.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class CarShopConverter {

    private final CarConverter carConverter;
    private final CarService carService;

    public CarShop toEntity(String shopName) {
        return CarShop.builder()
                .name(shopName)
                .build();
    }

    public CarShop toEntity(CarShop carShop, List<Integer> carIds) {
        Set<Car> cars = new HashSet<>();
        for (Integer carId : carIds) {
            cars.add(carService.getCar(carId));
        }
        cars.addAll(carShop.getCars());
        log.info("carShop.getCars().size() {}", carShop.getCars().size());
        return carShop.toBuilder()
                .cars(cars)
                .build();
    }

    public CarShopDto toDto(CarShop carShop) {
        return CarShopDto.builder()
                .name(carShop.getName())
                .cars(carShop.getCars() == null ? null : carConverter.toDto(List.copyOf(carShop.getCars())))
                .build();
    }

    public CarShopResponseDto toDto(Preorder preorder, boolean success) {
        var car = carService.getCar(preorder.getCarId());
        return CarShopResponseDto.builder()
                .success(success)
                .bankPreorderStatus(preorder.getBankPreorderStatus())
                .drivingSchoolPreorderStatus(preorder.getDrivingSchoolPreorderStatus())
                .GAIPreorderStatus(preorder.getGAIPreorderStatus())
                .message("Предзаказ на " + car.getColor()
                        + " " + car.getModel()
                        + " " + car.getName())
                .build();

    }

}
