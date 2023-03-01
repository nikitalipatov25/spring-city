package com.nikitalipatov.springcity.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PreorderResultDto {

    private boolean success;
    private int requestId;
    private String massage;
}
