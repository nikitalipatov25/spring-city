package com.nikitalipatov.springcity.converters;

import com.nikitalipatov.springcity.dtos.PersonDto;
import com.nikitalipatov.springcity.dtos.PersonRecord;
import com.nikitalipatov.springcity.models.Passport;
import com.nikitalipatov.springcity.models.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonConverter {


    public PersonDto toDto(Person person) {
        return PersonDto.builder()
                .name(person.getFullName())
                .number(person.getPassport().getNumber())
                .serial(person.getPassport().getSerial())
                .age(person.getAge())
                .car(person.getCar())
                .house(person.getHouse())
                .build();
    }

    public List<PersonDto> toDto(List<Person> personList) {
        var persons = new ArrayList<PersonDto>();
        for (Person person : personList) {
            persons.add(toDto(person));
        }
        return persons;
    }

    public Person edit(Person person, PersonRecord personRecord) {
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
