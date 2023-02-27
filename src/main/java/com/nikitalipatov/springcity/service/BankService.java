package com.nikitalipatov.springcity.service;

import com.nikitalipatov.springcity.dto.BankDto;
import com.nikitalipatov.springcity.dto.BankRecord;
import com.nikitalipatov.springcity.model.Bank;
import com.nikitalipatov.springcity.model.Person;

public interface BankService {

    BankDto createAccount(int personId, BankRecord bankRecord);

    BankDto addFounds(BankRecord bankRecord);

    Bank getBank(int bankId);
}
