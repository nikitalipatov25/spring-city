package com.nikitalipatov.springcity.controller;

import com.nikitalipatov.springcity.dto.BankDto;
import com.nikitalipatov.springcity.dto.BankRecord;
import com.nikitalipatov.springcity.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/bank")
public class BankController {

    private final BankService bankService;

    @PostMapping(value = "/person/{personId}/create_account")
    public BankDto createAccount(@PathVariable int personId, @RequestBody BankRecord bankRecord) {
        return bankService.createAccount(personId, bankRecord);
    }

    @PutMapping(value = "/founds/add")
    public BankDto addFounds(@RequestBody BankRecord bankRecord) {
        return bankService.addFounds(bankRecord);
    }
}
