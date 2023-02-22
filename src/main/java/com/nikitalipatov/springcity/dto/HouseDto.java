package com.nikitalipatov.springcity.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HouseDto {

    private String city;
    private String street;
    private String number;
}
