package com.nikitalipatov.springcity.services;

import com.nikitalipatov.springcity.dtos.HouseRecord;
import com.nikitalipatov.springcity.models.House;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public interface HouseService {

    List<House> getAll();

    House create(HouseRecord houseRecord);

    void delete(int id);

    House edit(int id, HouseRecord houseRecord);

    House getHouse(int houseId);
}
