package com.nikitalipatov.springcity.converter;

import com.nikitalipatov.springcity.dto.BankDto;
import com.nikitalipatov.springcity.dto.BankRecord;
import com.nikitalipatov.springcity.dto.CarDto;
import com.nikitalipatov.springcity.dto.CarRecord;
import com.nikitalipatov.springcity.model.Bank;
import com.nikitalipatov.springcity.model.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BankConverter {

    public Bank toEntity(Bank bank, BankRecord bankRecord) {
        return bank.toBuilder()
                .account(bankRecord.account())
                .name(bankRecord.name())
                .amount(bankRecord.amount())
                .person(bank.getPerson())
                .build();
    }

    public BankDto toDto(Bank bank) {
        return BankDto.builder()
                .bankAccount(bank.getAccount())
                .bankName(bank.getName())
                .cashAmount(bank.getAmount())
                .personName(bank.getPerson().getFullName())
                .build();
    }

    public List<BankDto> toDto(List<Bank> bankList) {
        var banks = new ArrayList<BankDto>();
        for (Bank bank : bankList) {
            banks.add(toDto(bank));
        }
        return banks;
    }
}
