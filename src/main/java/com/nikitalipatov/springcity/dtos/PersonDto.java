package com.nikitalipatov.springcity.dtos;

import com.nikitalipatov.springcity.models.Car;
import com.nikitalipatov.springcity.models.House;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
    private String name;
    private int age;
    private int serial;
    private int number;
    private Set<Car> car;
    private Set<House> house;
}
