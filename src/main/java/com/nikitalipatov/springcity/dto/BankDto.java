package com.nikitalipatov.springcity.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankDto {

    private int accountId;
    private String personName;
    private String bankName;
    private double cashAmount;
}
