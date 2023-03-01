package com.nikitalipatov.springcity.converter;

import com.nikitalipatov.springcity.dto.BankDto;
import com.nikitalipatov.springcity.model.BankAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BankConverter {

    public BankAccount toEntity(BankAccount bankAccount, double amount) {
        return bankAccount.toBuilder()
                .balance(amount)
                .build();
    }

    public BankDto toDto(BankAccount bankAccount) {
        return BankDto.builder()
                .accountId(bankAccount.getId())
                .bankName(bankAccount.getName())
                .cashAmount(bankAccount.getBalance())
                .personName(bankAccount.getPerson().getFullName())
                .build();
    }

    public List<BankDto> toDto(List<BankAccount> bankAccountList) {
        var banks = new ArrayList<BankDto>();
        for (BankAccount bankAccount : bankAccountList) {
            banks.add(toDto(bankAccount));
        }
        return banks;
    }
}
