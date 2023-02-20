package com.nikitalipatov.springcity.dtos;

import com.nikitalipatov.springcity.models.House;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonHouseDTO {
    private String name;
    private House house;
}
