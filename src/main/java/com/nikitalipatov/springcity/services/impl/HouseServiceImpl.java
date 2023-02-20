package com.nikitalipatov.springcity.services.impl;

import com.nikitalipatov.springcity.converters.HouseConverter;
import com.nikitalipatov.springcity.models.Car;
import com.nikitalipatov.springcity.services.HouseService;
import com.nikitalipatov.springcity.dtos.HouseRecord;
import com.nikitalipatov.springcity.exeptions.ResourceNotFoundException;
import com.nikitalipatov.springcity.models.House;
import com.nikitalipatov.springcity.models.Person;
import com.nikitalipatov.springcity.repos.HouseRepository;
import com.nikitalipatov.springcity.repos.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;
    private final PersonRepository personRepository;
    private final HouseConverter converter;

    @Override
    public List<House> getAll() {
        return houseRepository.findAll();
    }

    @Override
    public House create(HouseRecord houseRecord) {
        return houseRepository.save(new House(houseRecord.city(), houseRecord.street(), houseRecord.number()));
    }

    @Override
    @Transactional
    public void delete(int houseId) {
        var house = getHouse(houseId);
        Set<House> houseSet = new HashSet<>();
        houseSet.add(house);
        var persons = personRepository.findAllByHouseIn(houseId);
        for (int i = 0; i < persons.size(); i++) {
            persons.get(i).getHouse().removeIf(h -> h.getId() == houseId);
        }
        personRepository.saveAll(persons);
        houseRepository.delete(house);
    }

    @Override
    public House edit(int houseId, HouseRecord houseRecord) {
        var house = getHouse(houseId);
        return houseRepository.save(converter.edit(house, houseRecord));
    }

    @Override
    public House getHouse(int houseId) {
        return houseRepository.findById(houseId).orElseThrow(
                () -> new ResourceNotFoundException("No such house with id " + houseId)
        );
    }
}
