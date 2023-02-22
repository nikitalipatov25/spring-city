package com.nikitalipatov.springcity.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    private String gosNumber;
    private String model;
    private String name;
    private String type;
    private String color;
}
