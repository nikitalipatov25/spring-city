package com.nikitalipatov.springcity.contracts;

import com.nikitalipatov.springcity.dtos.HouseRecord;
import com.nikitalipatov.springcity.models.House;
import com.nikitalipatov.springcity.models.Person;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface HouseService {

    List<House> getAll();

    Optional<House> getById(int id);

    House create(HouseRecord houseRecord);

    HttpStatus delete(int id);

    House edit(int id, HouseRecord houseRecord);

//    Optional<House> addPerson(int id, Person person);
//
//    void deletePerson(int hid, int pid);
}
