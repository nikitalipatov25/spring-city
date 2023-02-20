package com.nikitalipatov.springcity.services.impl;

import com.nikitalipatov.springcity.converters.PersonConverter;
import com.nikitalipatov.springcity.dtos.PersonCarDTO;
import com.nikitalipatov.springcity.dtos.PersonDto;
import com.nikitalipatov.springcity.dtos.PersonRecord;
import com.nikitalipatov.springcity.exeptions.ResourceNotFoundException;
import com.nikitalipatov.springcity.models.*;
import com.nikitalipatov.springcity.repos.PersonRepository;
import com.nikitalipatov.springcity.services.CarService;
import com.nikitalipatov.springcity.services.HouseService;
import com.nikitalipatov.springcity.services.PassportService;
import com.nikitalipatov.springcity.services.PersonService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@NamedQuery(name = "findCarsByFullName", query = "SELECT p FROM Person p WHERE p.fullName = ?1")
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PassportService passportService;
    private final CarService carService;
    private final HouseService houseService;
    private final PersonConverter converter;

    @Override
    public List<PersonDto> findAllByStreet(String street) {
        return converter.toDto(personRepository.findAllByStreet(street));
    }

    @Override
    public List<PersonDto> getPassportDataByName(String personName) {
        var result = personRepository.findAllByFullNameLike(personName);
        return converter.toDto(result);
    }

    @Override
    public List<PersonDto> getAllByPersonName(String name) {
        var result = personRepository.findCarsByFullName(name);
        List<PersonDto> personCarDTOS = new ArrayList<>();
        for (Person person : result) {
            personCarDTOS.add(converter.toDto(person));
        }
        return personCarDTOS;
    }

    @Override
    public List<PersonDto> getAll() {
        converter.toDto(personRepository.findAll());
         return converter.toDto(personRepository.findAll());
    }

    @Override
    public PersonDto getByName(String name) {
        return converter.toDto(personRepository.findByFullName(name));
    }

    @Override
    @Transactional
    public PersonDto create(PersonRecord personRecord) {
        Person person = new Person(personRecord.fullName(), personRecord.age(),
                passportService.create(personRecord.passportRecord())
        );
        return converter.toDto(personRepository.save(person));
    }

    @Override
    public PersonDto addCar(int personId, int carId) {
        Person person = getPerson(personId);
        Car car = carService.getCar(carId);
        Set<Car> personCars = person.getCar();
        personCars.add(car);
        person.setCar(personCars);
        return converter.toDto(personRepository.save(person));
    }

    @Override
    public PersonDto addHouse(int personId, int houseId) {
        Person person = getPerson(personId);
        House house = houseService.getHouse(houseId);
        Set<House> houses = person.getHouse();
        houses.add(house);
        person.setHouse(houses);
        return converter.toDto(personRepository.save(person));
    }

    public void delete(int personId) {
        Person person = getPerson(personId);
        passportService.delete(person.getId());
        carService.delete(person.getCar().stream().map(Car::getId).collect(Collectors.toList()));
        person.setHouse(null);
        personRepository.delete(person);
    }

    public PersonDto edit(int personId, PersonRecord personRecord) {
        Person person = getPerson(personId);
        var result = converter.edit(person, personRecord);
        return converter.toDto(personRepository.save(result));
    }

    private Person getPerson(int personId) {
        return personRepository.findById(personId).orElseThrow(
                () -> new ResourceNotFoundException("No such car with id " + personId)
        );
    }
}
