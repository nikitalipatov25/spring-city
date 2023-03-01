package com.nikitalipatov.springcity.service;

import com.nikitalipatov.springcity.dto.BankDto;
import com.nikitalipatov.springcity.dto.BankRecord;
import com.nikitalipatov.springcity.enums.PreorderStatus;
import com.nikitalipatov.springcity.model.BankAccount;

import java.util.concurrent.CompletableFuture;

public interface BankService {

    BankDto createPersonAccount(int personId, BankRecord bankRecord);

    BankDto changeBalance(int accountId, double amount);

    CompletableFuture<PreorderStatus> checkBalance(int bankAccount, double carPrice);

    BankAccount getBank(int bankId);
}
