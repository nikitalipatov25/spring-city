package com.nikitalipatov.springcity.services;

import com.nikitalipatov.springcity.dtos.HouseDto;
import com.nikitalipatov.springcity.dtos.HouseRecord;
import com.nikitalipatov.springcity.models.House;

import java.util.List;

public interface HouseService {

    List<HouseDto> getAll();

    HouseDto create(HouseRecord houseRecord);

    void delete(int houseId);

    HouseDto edit(int houseId, HouseRecord houseRecord);

    House getHouse(int houseId);
}
