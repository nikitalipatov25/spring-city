package com.nikitalipatov.springcity.contracts;

import com.nikitalipatov.springcity.dtos.PassportRecord;
import com.nikitalipatov.springcity.dtos.PersonRecord;
import com.nikitalipatov.springcity.models.Car;
import com.nikitalipatov.springcity.models.Person;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    List<Person> getAll();

    Person getByName(String name);

    Person create(PersonRecord personRecord);

    Person addCar(int id, String gosNumber);

    Person addHouse(int pid, int hid);

    HttpStatus delete(int id);

    Person edit(int id, PersonRecord personRecord);

    List<Car> getAllByPersonName(String name);
    List<Person> getAllByChar(char c);

//    void removeFromHouse(int id);

}
