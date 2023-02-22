package com.nikitalipatov.springcity.service;

import com.nikitalipatov.springcity.dto.HouseDto;
import com.nikitalipatov.springcity.dto.HouseRecord;
import com.nikitalipatov.springcity.model.House;

import java.util.List;

public interface HouseService {

    List<HouseDto> getAll();

    HouseDto create(HouseRecord houseRecord);

    void delete(int houseId);

    HouseDto edit(int houseId, HouseRecord houseRecord);

    House getHouse(int houseId);
}
