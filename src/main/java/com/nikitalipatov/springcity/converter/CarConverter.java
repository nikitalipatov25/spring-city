package com.nikitalipatov.springcity.converter;

import com.nikitalipatov.springcity.dto.CarDto;
import com.nikitalipatov.springcity.dto.CarRecord;
import com.nikitalipatov.springcity.model.Car;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarConverter {

    public Car toEntity(Car car, CarRecord carRecord) {
        return car.toBuilder()
                .color(carRecord.color())
                .model(carRecord.model())
                .name(carRecord.name())
                .gosNumber(carRecord.gosNumber())
                .model(carRecord.model())
                .build();
    }

    public CarDto toDto(Car car) {
        return CarDto.builder()
                .gosNumber(car.getGosNumber())
                .name(car.getName())
                .color(car.getColor())
                .model(car.getModel())
                .type(car.getType())
                .build();
    }

    public List<CarDto> toDto(List<Car> carList) {
        var cars = new ArrayList<CarDto>();
        for (Car car : carList) {
            cars.add(toDto(car));
        }
        return cars;
    }
}
