package com.nikitalipatov.springcity.services;

import com.nikitalipatov.springcity.dtos.PersonCarDTO;
import com.nikitalipatov.springcity.dtos.PersonRecord;
import com.nikitalipatov.springcity.dtos.PersonDto;
import com.nikitalipatov.springcity.models.Person;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface PersonService {

    List<PersonDto> getAll();

    PersonDto getByName(String name);

    PersonDto create(PersonRecord personRecord);

    PersonDto addCar(int personId, int carId);

    PersonDto addHouse(int personId, int houseId);

    void delete(int id);

    PersonDto edit(int id, PersonRecord personRecord);

    List<PersonDto> getAllByPersonName(String name);

    List<PersonDto> getPassportDataByName(String text);

    List<PersonDto> findAllByStreet(String street);


}
