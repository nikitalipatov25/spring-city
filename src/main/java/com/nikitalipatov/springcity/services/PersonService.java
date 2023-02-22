package com.nikitalipatov.springcity.services;

import com.nikitalipatov.springcity.dtos.CarRecord;
import com.nikitalipatov.springcity.dtos.PersonDto;
import com.nikitalipatov.springcity.dtos.PersonRecord;

import java.util.List;

public interface PersonService {

    List<PersonDto> getAll();

    PersonDto getByName(String name);

    PersonDto create(PersonRecord personRecord);

    PersonDto addHouse(int personId, int houseId);

    void delete(int id);

    PersonDto edit(int id, PersonRecord personRecord);

    List<PersonDto> getAllCarsByPersonName(String name);

    List<PersonDto> getPassportDataByName(String text);

    List<PersonDto> findAllByStreet(String street);


}
