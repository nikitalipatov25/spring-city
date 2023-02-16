package com.nikitalipatov.springcity.controllers;

import com.nikitalipatov.springcity.contracts.PersonService;
import com.nikitalipatov.springcity.dtos.PersonRecord;
import com.nikitalipatov.springcity.models.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/person")
public class PersonController {

    private final PersonService personService;

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

    @PutMapping(value = "/edit/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Person edit(@PathVariable int id, @RequestBody PersonRecord personRecord) {
//        что можно менять: фио, возраст, в паспорте все
        return null;
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void delete(@PathVariable int id) {
        //При удалении жителя удаляются его паспорт и машины, обновляются списки хозяев домов (обернуть в транзакцию).
    }
}
