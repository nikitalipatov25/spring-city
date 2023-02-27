package com.nikitalipatov.springcity.converter;

import com.nikitalipatov.springcity.dto.*;
import com.nikitalipatov.springcity.model.Passport;
import com.nikitalipatov.springcity.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonConverter {

    private final CarConverter carConverter;
    private final HouseConverter houseConverter;
    private final BankConverter bankConverter;

    public PersonDto toDto(Person person) {
        return PersonDto.builder()
                .name(person.getFullName())
                .number(person.getPassport().getNumber())
                .serial(person.getPassport().getSerial())
                .sex(person.getPassport().getSex())
                .age(person.getAge())
                .car(person.getCar() != null ? carConverter.toDto(person.getCar().stream().toList()) : null)
                .house(person.getHouse() != null ? houseConverter.toDto(person.getHouse().stream().toList()) : null)
                .bank(person.getBank() != null ? bankConverter.toDto(person.getBank().stream().toList()) : null)
                .build();
    }

    public List<PersonDto> toDto(List<Person> personList) {
        var persons = new ArrayList<PersonDto>();
        for (Person person : personList) {
            persons.add(toDto(person));
        }
        return persons;
    }

    public PersonPassportDto toPersonPassportDto(Person person) {
        return PersonPassportDto.builder()
                .name(person.getFullName())
                .passportNumber(person.getPassport().getNumber())
                .passportSerial(person.getPassport().getSerial())
                .sex(person.getPassport().getSex())
                .age(person.getAge())
                .build();
    }

    public List<PersonPassportDto> toPersonPassportDto(List<Person> personList) {
        var persons = new ArrayList<PersonPassportDto>();
        for (Person person : personList) {
            persons.add(toPersonPassportDto(person));
        }
        return persons;
    }

    public PersonHouseDto toPersonHouseDto(Person person, String street) {
        return PersonHouseDto.builder()
                .name(person.getFullName())
                .houses(houseConverter.toDto(person.getHouse().stream().filter(h -> h.getStreet().equals(street)).toList()))
                .build();
    }

    public List<PersonHouseDto> toPersonHouseDto(List<Person> personList, String street) {
        var persons = new ArrayList<PersonHouseDto>();
        for (Person person : personList) {
            persons.add(toPersonHouseDto(person, street));
        }
        return persons;
    }

    public PersonHouseDto toPersonHouseDto(Person person) {
        return PersonHouseDto.builder()
                .name(person.getFullName())
                .houses(houseConverter.toDto(person.getHouse().stream().toList()))
                .build();
    }

    public List<PersonHouseDto> toPersonHouseDto(List<Person> personList) {
        var persons = new ArrayList<PersonHouseDto>();
        for (Person person : personList) {
            persons.add(toPersonHouseDto(person));
        }
        return persons;
    }

    public PersonCarDto toPersonCarDto(Person person) {
        return PersonCarDto.builder()
                .name(person.getFullName())
                .carList(carConverter.toDto(person.getCar().stream().toList()))
                .build();
    }

    public List<PersonCarDto> toPersonCarDto(List<Person> personList) {
        var persons = new ArrayList<PersonCarDto>();
        for (Person person : personList) {
            persons.add(toPersonCarDto(person));
        }
        return persons;
    }

    public Person toEntity(Person person, PersonRecord personRecord) {
        person.setFullName(personRecord.fullName());
        person.setAge(personRecord.age());

        Passport passport = person.getPassport();
        passport.setAddress(personRecord.passportRecord().address());
        passport.setAddressFact(personRecord.passportRecord().addressFact());
        passport.setDateOfBirth(personRecord.passportRecord().dateOfBirth());
        passport.setIssued(personRecord.passportRecord().issued());
        passport.setIssuedBy(personRecord.passportRecord().issuedBy());
        passport.setNumber(personRecord.passportRecord().number());
        passport.setSerial(personRecord.passportRecord().serial());
        passport.setSex(personRecord.passportRecord().sex());
        passport.setPlaceOfBirth(personRecord.passportRecord().placeOfBirth());

        person.setPassport(passport);
        return person;
    }

}
