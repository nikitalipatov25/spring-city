package com.nikitalipatov.springcity.controllers;

import com.nikitalipatov.springcity.contracts.HouseService;
import com.nikitalipatov.springcity.dtos.HouseRecord;
import com.nikitalipatov.springcity.models.House;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/house")
@RequiredArgsConstructor
public class HouseController {

    private final HouseService houseService;

    @GetMapping(value = "/list")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<House> getAll() {
        return houseService.getAll();
    }

    @PostMapping(value = "/create")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public House create(@RequestBody HouseRecord houseRecord) {
        return houseService.create(houseRecord);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public HttpStatus delete(@PathVariable int id) {
        return houseService.delete(id);
    }

    @PutMapping(value = "/edit/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public House edit(@PathVariable int id, @RequestBody HouseRecord houseRecord) {
        return houseService.edit(id, houseRecord);
    }
}
