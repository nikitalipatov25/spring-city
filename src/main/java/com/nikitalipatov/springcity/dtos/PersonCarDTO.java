package com.nikitalipatov.springcity.dtos;

import com.nikitalipatov.springcity.models.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonCarDTO {
    private String name;
    private Set<Car> cars;
//    private String gosNumber;
//    private String carName;
//    private String model;
//    private String color;
}
