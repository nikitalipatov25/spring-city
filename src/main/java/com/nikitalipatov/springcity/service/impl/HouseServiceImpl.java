package com.nikitalipatov.springcity.service.impl;

import com.nikitalipatov.springcity.converter.HouseConverter;
import com.nikitalipatov.springcity.dto.HouseDto;
import com.nikitalipatov.springcity.dto.HouseRecord;
import com.nikitalipatov.springcity.exeption.ResourceNotFoundException;
import com.nikitalipatov.springcity.model.House;
import com.nikitalipatov.springcity.model.Person;
import com.nikitalipatov.springcity.repository.HouseRepository;
import com.nikitalipatov.springcity.repository.PersonRepository;
import com.nikitalipatov.springcity.service.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;
    private final PersonRepository personRepository;
    private final HouseConverter converter;

    @Override
    public List<HouseDto> getAll() {
        return converter.toDto(houseRepository.findAll());
    }

    @Override
    @Transactional
    public HouseDto create(HouseRecord houseRecord) {
        House house = new House(houseRecord.city(), houseRecord.street(), houseRecord.number());
        return converter.toDto(houseRepository.save(house));
    }

    @Override
    @Transactional
    public void delete(int houseId) {
        var house = getHouse(houseId);
        var persons = personRepository.findAllByHouseIn(houseId);
        for (Person person : persons) {
            person.getHouse().removeIf(h -> h.getId() == houseId);
        }
        personRepository.saveAll(persons);
        houseRepository.delete(house);
    }

    @Override
    @Transactional
    public HouseDto edit(int houseId, HouseRecord houseRecord) {
        var house = getHouse(houseId);
        return converter.toDto(houseRepository.save(converter.toEntity(house, houseRecord)));
    }

    @Override
    public House getHouse(int houseId) {
        return houseRepository.findById(houseId).orElseThrow(
                () -> new ResourceNotFoundException("No such house with id " + houseId)
        );
    }
}
