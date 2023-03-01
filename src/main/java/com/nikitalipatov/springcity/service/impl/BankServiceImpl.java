package com.nikitalipatov.springcity.service.impl;

import com.nikitalipatov.springcity.converter.BankConverter;
import com.nikitalipatov.springcity.dto.BankDto;
import com.nikitalipatov.springcity.dto.BankRecord;
import com.nikitalipatov.springcity.enums.PreorderStatus;
import com.nikitalipatov.springcity.exeption.ResourceNotFoundException;
import com.nikitalipatov.springcity.model.BankAccount;
import com.nikitalipatov.springcity.repository.BankRepository;
import com.nikitalipatov.springcity.service.BankService;
import com.nikitalipatov.springcity.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;
    private final PersonService personService;
    private final BankConverter converter;

    @Override
    @Transactional
    public BankDto createPersonAccount(int personId, BankRecord bankRecord) {
        BankAccount bankAccount = new BankAccount(bankRecord.accountName(), bankRecord.balance());
        bankAccount.setPerson(personService.getPerson(personId));
        return converter.toDto(bankRepository.save(bankAccount));
    }

    @Override
    @Transactional
    public BankDto changeBalance(int accountId, double amount) {
        BankAccount bankAccount = getBank(accountId);
        return converter.toDto(bankRepository.save(converter.toEntity(bankAccount, amount)));
    }

    @Override
    public CompletableFuture<PreorderStatus> checkBalance(int bankAccount, double carPrice) {
        return CompletableFuture.supplyAsync(() -> checkBankStatusTask(bankAccount, carPrice));
    }

    @Override
    public BankAccount getBank(int bankAccount) {
        return bankRepository.findById(bankAccount).orElseThrow(
                () -> new ResourceNotFoundException("No such bank with accountId " + bankAccount)
        );
    }

    private PreorderStatus checkBankStatusTask(int bankAccount, double carPrice) {
        BankAccount bank = getBank(bankAccount);
        PreorderStatus bankStatus;
        if (bank.getBalance() >= carPrice) {
            bankStatus = PreorderStatus.SUCCESS;
        } else {
            bankStatus = PreorderStatus.FAIL;
        }
        try {
            log.info("checkBalance processing ... ");
            Thread.sleep(5000);
            log.info("checkBalance complete ... ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return bankStatus;
    }

}
