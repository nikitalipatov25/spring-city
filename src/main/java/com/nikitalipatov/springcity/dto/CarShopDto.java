package com.nikitalipatov.springcity.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarShopDto {

    private String name;
    private List<CarDto> cars;
}
