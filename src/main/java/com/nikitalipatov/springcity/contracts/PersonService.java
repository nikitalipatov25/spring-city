package com.nikitalipatov.springcity.contracts;

import com.nikitalipatov.springcity.dtos.PassportRecord;
import com.nikitalipatov.springcity.dtos.PersonRecord;
import com.nikitalipatov.springcity.models.Person;

import java.util.List;

public interface PersonService {

    List<Person> getAll();

    Person getByName(String name);

    Person create(PersonRecord personRecord);

    Person addCar(int id, String gosNumber);

}
