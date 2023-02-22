package com.nikitalipatov.springcity.converter;

import com.nikitalipatov.springcity.dto.HouseDto;
import com.nikitalipatov.springcity.dto.HouseRecord;
import com.nikitalipatov.springcity.model.House;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HouseConverter {

    public House toEntity(House house, HouseRecord houseRecord) {
        return house.toBuilder()
                .city(houseRecord.city())
                .street(houseRecord.street())
                .number(houseRecord.number())
                .build();
    }

    public HouseDto toDto(House house) {
        return HouseDto.builder()
                .city(house.getCity())
                .street(house.getStreet())
                .number(house.getNumber())
                .build();
    }

    public List<HouseDto> toDto(List<House> houseList) {
        var houses = new ArrayList<HouseDto>();
        for (House house : houseList) {
            houses.add(toDto(house));
        }
        return houses;
    }
}
