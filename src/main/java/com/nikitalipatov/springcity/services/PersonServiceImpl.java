package com.nikitalipatov.springcity.services;

import com.nikitalipatov.springcity.contracts.CarService;
import com.nikitalipatov.springcity.contracts.HouseService;
import com.nikitalipatov.springcity.contracts.PassportService;
import com.nikitalipatov.springcity.contracts.PersonService;
import com.nikitalipatov.springcity.dtos.PassportRecord;
import com.nikitalipatov.springcity.dtos.PersonRecord;
import com.nikitalipatov.springcity.exeptions.ResourceNotFoundException;
import com.nikitalipatov.springcity.models.Car;
import com.nikitalipatov.springcity.models.House;
import com.nikitalipatov.springcity.models.Passport;
import com.nikitalipatov.springcity.models.Person;
import com.nikitalipatov.springcity.repos.CarRepository;
import com.nikitalipatov.springcity.repos.PersonRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.hibernate.Cache;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PassportService passportService;
    private final CarService carService;
    private final HouseService houseService;
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Person> getAllByChar(char c) {
        return null;
    }

    @Override
    public List<Car> getAllByPersonName(String name) {
        var result = entityManager.createNamedQuery("Person.findByFullName");
        var a = result.getResultList();
        System.out.println(a.toString());
        System.out.println(result);
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));
        }
        return null;
    }

    @Override
    public List<Person> getAll() {
        var result = personRepository.findAll();
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("No content");
        } else {
            return result;
        }
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
        if (person.isEmpty() || car.isEmpty()) {
            throw new ResourceNotFoundException("Person with id " + id + " or car with number " + gosNumber + " does not exist");
        } else {
            Set<Car> cars = person.get().getCar();
            cars.add(car.get());
            person.get().setCar(cars);
            return personRepository.save(person.get());
        }
    }

    @Override
    public Person addHouse(int pid, int hid) {
        Optional<Person> person = personRepository.findById(pid);
        Optional<House> house = houseService.getById(hid);
        if (person.isEmpty() || house.isEmpty()) {
            throw new ResourceNotFoundException("Person with id " + pid + " or house with id " + hid + " does not exist");
        } else {
            Set<House> houses = person.get().getHouse();
            houses.add(house.get());
            person.get().setHouse(houses);
            return personRepository.save(person.get());
        }
    }

    public HttpStatus delete(int id) {
        Optional<Person> person = personRepository.findById(id);
        return person
                .map(e -> {
                    passportService.delete(person.get().getId());
                    carService.delete(person.get().getCar());
                    person.get().setHouse(null);
                    personRepository.delete(person.get());
                    return HttpStatus.NO_CONTENT;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Person with id " + id +  " does not exist"));
    }

    public Person edit(int id, PersonRecord personRecord) {
        Optional<Person> person = personRepository.findById(id);
        return person
                .map(e -> {
                    e.setFullName(personRecord.fullName());
                    e.setAge(personRecord.age());
                    Passport passport = person.get().getPassport();
                    passport.setAddress(personRecord.passportRecord().address());
                    passport.setAddressFact(personRecord.passportRecord().addressFact());
                    passport.setDateOfBirth(personRecord.passportRecord().dateOfBirth());
                    passport.setIssued(personRecord.passportRecord().issued());
                    passport.setIssuedBy(personRecord.passportRecord().issuedBy());
                    passport.setNumber(personRecord.passportRecord().number());
                    passport.setSerial(personRecord.passportRecord().serial());
                    passport.setSex(personRecord.passportRecord().sex());
                    passport.setPlaceOfBirth(personRecord.passportRecord().placeOfBirth());
                    e.setPassport(passport);
                    return personRepository.save(e);
                })
                .orElseThrow(() -> new ResourceNotFoundException("No such house with id " + id));
    }

//    public void removeFromHouse(int id) {
//        var person = personRepository.findAll();
//        for (int i = 0; i < person.size(); i++) {
//            person.get(i).getHouse().removeIf(h -> h.getId() == id);
//        }
//        personRepository.saveAll(person);
//    }
}
