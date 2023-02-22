package com.nikitalipatov.springcity.controllers;

import com.nikitalipatov.springcity.dtos.CarRecord;
import com.nikitalipatov.springcity.services.PersonService;
import com.nikitalipatov.springcity.dtos.PersonRecord;
import com.nikitalipatov.springcity.dtos.PersonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/person")
public class PersonController {

    private final PersonService personService;

    @GetMapping(value = "/custom/house/{street}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<PersonDto> getAllByStreet(@PathVariable String street) {
        return personService.findAllByStreet(street);
    }

    @GetMapping(value = "/custom/{name}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<PersonDto> getAllByPersonName(@PathVariable String name) {
        return personService.getAllCarsByPersonName(name);
    }

    @GetMapping(value = "/custom/list/{personName}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<PersonDto> getPassportDataByName(@PathVariable String personName) {
        return personService.getPassportDataByName(personName);
    }

    @GetMapping(value = "/list")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<PersonDto> getAll() {
        return personService.getAll();
    }

    @GetMapping(value = "/{name}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public PersonDto getByName(@PathVariable String name) {
        return personService.getByName(name);
    }

    @PostMapping(value = "/create")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public PersonDto create(@RequestBody PersonRecord personRecord) {
        return personService.create(personRecord);
    }

    @PutMapping(value = "/add/{pid}/house/{hid}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public PersonDto addHouse(@PathVariable int pid, @PathVariable int hid) {
        return personService.addHouse(pid, hid);
    }

    @PutMapping(value = "/edit/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public PersonDto edit(@PathVariable int id, @RequestBody PersonRecord personRecord) {
        return personService.edit(id, personRecord);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void delete(@PathVariable int id) {
        personService.delete(id);
    }
}
