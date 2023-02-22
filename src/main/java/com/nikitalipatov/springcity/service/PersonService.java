package com.nikitalipatov.springcity.service;

import com.nikitalipatov.springcity.dto.*;

import java.util.List;

public interface PersonService {

    List<PersonDto> getAll();

    PersonDto getByName(String name);

    PersonDto create(PersonRecord personRecord);

    PersonDto addHouse(int personId, int houseId);

    void delete(int id);

    PersonDto edit(int id, PersonRecord personRecord);

    List<PersonCarDto> getAllCarsByPersonName(String personName);

    List<PersonPassportDto> getPassportDataByName(String personName);

    List<PersonHouseDto> findAllByStreet(String street);

    List<PersonHouseDto> getHousesByPersonName(String personName);


}
