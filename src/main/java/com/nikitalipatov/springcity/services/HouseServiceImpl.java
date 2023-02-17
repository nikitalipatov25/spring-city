package com.nikitalipatov.springcity.services;

import com.nikitalipatov.springcity.contracts.HouseService;
import com.nikitalipatov.springcity.contracts.PersonService;
import com.nikitalipatov.springcity.dtos.HouseRecord;
import com.nikitalipatov.springcity.exeptions.ResourceNotFoundException;
import com.nikitalipatov.springcity.models.House;
import com.nikitalipatov.springcity.models.Person;
import com.nikitalipatov.springcity.repos.HouseRepository;
import com.nikitalipatov.springcity.repos.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;
    private final PersonRepository personRepository;

    @Override
    public List<House> getAll() {
        var result = houseRepository.findAll();
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("No content");
        } else {
            return result;
        }
    }

    @Override
    public Optional<House> getById(int id) {
        return houseRepository.findById(id);
    }

    @Override
    public House create(HouseRecord houseRecord) {
        House house = new House(houseRecord.city(), houseRecord.street(), houseRecord.number());
        return houseRepository.save(house);
    }

    @Override
    public HttpStatus delete(int id) {
        var house = houseRepository.findById(id);
        return house
                .map(e -> {
                    var persons = personRepository.findAll();
                    for (Person person : persons) {
                        person.getHouse().removeIf(h -> h.getId() == id);
                    }
                    personRepository.saveAll(persons);
                    houseRepository.delete(house.get());
                    return HttpStatus.NO_CONTENT;
                })
                .orElseThrow(() -> new ResourceNotFoundException("No such house with id " + id));
    }

    @Override
    public House edit(int id, HouseRecord houseRecord) {
        var house = houseRepository.findById(id);
        return house
                    .map(e -> {
                        e.setCity(houseRecord.city());
                        e.setStreet(houseRecord.street());
                        e.setNumber(houseRecord.number());
                        return houseRepository.save(e);
                    })
                    .orElseThrow(() -> new ResourceNotFoundException("No such house with id " + id));
    }
//    @Override
//    public Optional<House> addPerson(int id, Person person) {
//        Optional<House> house  = houseRepository.findById(id);
//        return house
//                .map(e -> {
//                    var persons = e.getPerson();
//                    persons.add(person);
//                    e.setPerson(persons);
//                    return houseRepository.save(e);
//                });
//    }
//
//    public void deletePerson(int hid, int pid) {
//        Optional<House> house = houseRepository.findById(hid);
//        house.map(e -> {
//            var persons = e.getPerson();
//            persons.stream().filter(p );
//        })
//    }

//    @Override
//    public void delete(Set<House> houses) {
//        Optional<House> house;
//        var houseList = houses.stream().toList();
//        for (int i = 0; i < houseList.size(); i++) {
//            house = houseRepository.findById(houseList.get(i).getId());
//            houseRepository.delete(house.get());
//        }
//    }
}
