package com.nikitalipatov.springcity.controllers;

import com.nikitalipatov.springcity.contracts.PersonService;
import com.nikitalipatov.springcity.dtos.PersonRecord;
import com.nikitalipatov.springcity.models.Car;
import com.nikitalipatov.springcity.models.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/person")
public class PersonController {

    private final PersonService personService;

    @GetMapping(value = "/custom/{name}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<Car> getAllByPersonName(@PathVariable String name) {
        return personService.getAllByPersonName(name);
    }

    @GetMapping(value = "/custom/list/{c}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<Person> getAllByChar(@PathVariable char c) {
        return personService.getAllByChar(c);
    }

    @GetMapping(value = "/list")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<Person> getAll() {
        return personService.getAll();
    }

    @GetMapping(value = "/{name}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Person getByName(@PathVariable String name) {
        return personService.getByName(name);
    }

    @PostMapping(value = "/create")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Person create(@RequestBody PersonRecord personRecord) {
        return personService.create(personRecord);
    }

    @PutMapping(value = "/add/{id}/car/{gosNumber}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Person addCar(@PathVariable int id, @PathVariable String gosNumber) {
        return personService.addCar(id, gosNumber);
    }

    @PutMapping(value = "/add/{pid}/house/{hid}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Person addHouse(@PathVariable int pid, @PathVariable int hid) {
        return personService.addHouse(pid, hid);
    }

    @PutMapping(value = "/edit/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Person edit(@PathVariable int id, @RequestBody PersonRecord personRecord) {
        return personService.edit(id, personRecord);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void delete(@PathVariable int id) {
        personService.delete(id);
    }
}
