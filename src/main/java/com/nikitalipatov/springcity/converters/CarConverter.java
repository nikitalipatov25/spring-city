package com.nikitalipatov.springcity.converters;

import com.nikitalipatov.springcity.dtos.CarRecord;
import com.nikitalipatov.springcity.models.Car;
import org.springframework.stereotype.Component;

@Component
public class CarConverter {

    public Car edit(Car car, CarRecord carRecord) {
        car.setType(carRecord.type());
        car.setModel(carRecord.model());
        car.setName(carRecord.name());
        car.setColor(carRecord.color());
        car.setGosNumber(carRecord.gosNumber());
        return car;
    }
}
