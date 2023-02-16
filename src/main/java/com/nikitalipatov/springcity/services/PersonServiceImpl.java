package com.nikitalipatov.springcity.services;

import com.nikitalipatov.springcity.contracts.CarService;
import com.nikitalipatov.springcity.contracts.PersonService;
import com.nikitalipatov.springcity.dtos.PassportRecord;
import com.nikitalipatov.springcity.dtos.PersonRecord;
import com.nikitalipatov.springcity.models.Car;
import com.nikitalipatov.springcity.models.Person;
import com.nikitalipatov.springcity.repos.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PassportServiceImpl passportService;
    private final CarService carService;

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @Override
    public Person getByName(String name) {
        return personRepository.findByFullName(name);
    }

    @Override
    public Person create(PersonRecord personRecord) {
        Person person = new Person(personRecord.fullName(), personRecord.age(),
                passportService.create(personRecord.passportRecord())
        );
        return personRepository.save(person);
    }

    @Override
    public Person addCar(int id, String gosNumber) {
        Optional<Person> person = personRepository.findById(id);
        Optional<Car> car = carService.getByGosNumber(gosNumber);
        Set<Car> cars = person.get().getCar();
        cars.add(car.get());
        person.get().setCar(cars);
        if (person.get().getFullName().equals("") || car.get().getGosNumber().equals("")) {
            throw new RuntimeException("Person or car not exist");
        } else return personRepository.save(person.get());
    }
}
