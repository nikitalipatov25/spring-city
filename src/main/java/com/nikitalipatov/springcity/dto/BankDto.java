package com.nikitalipatov.springcity.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankDto {

    private String personName;
    private String bankName;
    private int bankAccount;
    private double cashAmount;
}
