package com.nikitalipatov.springcity.converters;

import com.nikitalipatov.springcity.dtos.HouseRecord;
import com.nikitalipatov.springcity.models.House;
import org.springframework.stereotype.Component;

@Component
public class HouseConverter {

    public House edit(House house, HouseRecord houseRecord) {
        house.setCity(houseRecord.city());
        house.setStreet(houseRecord.street());
        house.setNumber(houseRecord.number());
        return house;
    }
}
