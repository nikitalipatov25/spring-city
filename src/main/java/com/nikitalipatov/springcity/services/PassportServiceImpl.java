package com.nikitalipatov.springcity.services;

import com.nikitalipatov.springcity.contracts.PassportService;
import com.nikitalipatov.springcity.dtos.PassportRecord;
import com.nikitalipatov.springcity.models.Passport;
import com.nikitalipatov.springcity.repos.PassportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PassportServiceImpl implements PassportService {

    private final PassportRepository passportRepository;

    @Override
    public Passport create(PassportRecord passportRecord) {
        Passport passport = new Passport(passportRecord.serial(), passportRecord.number(),
                passportRecord.address(), passportRecord.addressFact(),
                passportRecord.placeOfBirth(), passportRecord.dateOfBirth(), passportRecord.sex(),
                passportRecord.issued(), passportRecord.issuedBy());
        return passportRepository.save(passport);
    }

    @Override
    public void delete(int id) {
        Optional<Passport> passport = passportRepository.findById(id);
        passportRepository.delete(passport.get());
    }
}
