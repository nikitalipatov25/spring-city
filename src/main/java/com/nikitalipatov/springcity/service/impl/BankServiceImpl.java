package com.nikitalipatov.springcity.service.impl;

import com.nikitalipatov.springcity.converter.BankConverter;
import com.nikitalipatov.springcity.dto.BankDto;
import com.nikitalipatov.springcity.dto.BankRecord;
import com.nikitalipatov.springcity.exeption.ResourceNotFoundException;
import com.nikitalipatov.springcity.model.Bank;
import com.nikitalipatov.springcity.model.Person;
import com.nikitalipatov.springcity.repository.BankRepository;
import com.nikitalipatov.springcity.repository.PersonRepository;
import com.nikitalipatov.springcity.service.BankService;
import com.nikitalipatov.springcity.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;
    private final PersonService personService;
    private final BankConverter converter;

    @Override
    @Transactional
    public BankDto createAccount(int personId, BankRecord bankRecord) {
        Bank bank = new Bank(bankRecord.name(), bankRecord.amount(), bankRecord.account());
        bank.setPerson(personService.getPerson(personId));
        return converter.toDto(bankRepository.save(bank));
    }

    @Override
    @Transactional
    public BankDto addFounds(BankRecord bankRecord) {
        Bank bank = getBank(bankRecord.account());
        return converter.toDto(bankRepository.save(converter.toEntity(bank, bankRecord)));
    }

    @Override
    public Bank getBank(int bankAccount) {
        return bankRepository.findByAccount(bankAccount).orElseThrow(
                () -> new ResourceNotFoundException("No such bank with account " + bankAccount)
        );
    }


}
