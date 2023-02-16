package com.nikitalipatov.springcity.services;

import com.nikitalipatov.springcity.dtos.PassportRecord;
import com.nikitalipatov.springcity.models.Passport;
import com.nikitalipatov.springcity.repos.PassportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class PassportServiceImpl {

    private final PassportRepository passportRepository;

    public Passport create(PassportRecord passportRecord) {
        Passport passport = new Passport(passportRecord.serial(), passportRecord.number(),
                passportRecord.address(), passportRecord.addressFact(),
                passportRecord.placeOfBirth(), passportRecord.dateOfBirth(), passportRecord.sex(),
                passportRecord.issued(), passportRecord.issuedBy());
        return passportRepository.save(passport);
    }
}
