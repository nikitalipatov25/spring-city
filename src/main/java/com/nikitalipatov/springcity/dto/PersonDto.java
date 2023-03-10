package com.nikitalipatov.springcity.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
    private String name;
    private int age;
    private String sex;
    private int serial;
    private int number;
    private List<CarDto> car;
    private List<HouseDto> house;
    private List<BankDto> bank;
}
